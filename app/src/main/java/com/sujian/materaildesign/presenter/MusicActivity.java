package com.sujian.materaildesign.presenter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.nineoldandroids.view.ViewHelper;
import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.constant.Constant;
import com.sujian.materaildesign.delegate.MusicDalegale;
import com.sujian.materaildesign.frame.presenter.ActivityPresenter;
import com.sujian.materaildesign.model.music.Song;
import com.sujian.materaildesign.player.ManagedMediaPlayer;
import com.sujian.materaildesign.player.MusicPlayer;
import com.sujian.materaildesign.player.PlayEvent;
import com.sujian.materaildesign.player.ReflashViewEvent;
import com.sujian.materaildesign.uitls.MediaUtil;
import com.sujian.materaildesign.widget.CustomRelativeLayout;
import com.sujian.materaildesign.widget.CustomSettingView;
import com.sujian.materaildesign.widget.LyricView;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * 音乐presenter
 */
public class MusicActivity extends ActivityPresenter<MusicDalegale> implements SeekBar.OnSeekBarChangeListener,LyricView.OnPlayerClickListener {

    @BindView(R.id.tb_music)
    Toolbar tb_music;
    @BindView(R.id.iv_pre)
    ImageView iv_pre;
    @BindView(R.id.iv_play)
    ImageView iv_play;
    @BindView(R.id.iv_next)
    ImageView iv_next;
    @BindView(R.id.lyricview)
    LyricView lyricView;
    @BindView(R.id.seekbar)
    SeekBar seekBar;
    @BindView(R.id.tv_crr_time)
    TextView tv_crr_time;
    @BindView(R.id.tv_all_time)
    TextView tv_all_time;
    @BindView(R.id.setting_layout)
    ViewStub setting_layout;

    private CustomSettingView customSettingView;
    private CustomRelativeLayout customRelativeLayout;

    private ValueAnimator press_animator, up_animator;
    private long animatorDuration = 120;

    private SearchView searchView;
    //宽度

    private int searchWidth;
    private MusicPlayer musicPlayer;
    private boolean showLyc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.initWindow();
        RxBus.get().register(this);
    }

    @Override
    protected Class<MusicDalegale> getDelegateClass() {
        return MusicDalegale.class;
    }


    @Override
    protected void initView() {
        initMpv();
    }


    private void initMpv() {

        seekBar.setOnSeekBarChangeListener(this);
        lyricView.setOnPlayerClickListener(this);

        musicPlayer = MusicPlayer.getMusicPlayer();
        Song song = musicPlayer.getNowPlaying();
        if (song == null)
            return;

        tb_music.setTitle(song.getTitle());
        tv_all_time.setText(MediaUtil.formaTime(musicPlayer.getDuration()));
        seekBar.setMax(musicPlayer.getDuration());

        if (song.getMusicType() == Song.MusicType.NetworkMusic) {

            viewDelegate.initBG(song.getPicUrl());
            MediaUtil.getInputStreamByUrl(song.getLycUrl(),
                    new Subscriber<InputStream>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(InputStream inputStream) {
                            Logger.e("得到文件歌词输入流" + inputStream.toString());
                            lyricView.setLyricInputStream(inputStream, "UTF-8");
                        }
                    });


        }

        if (musicPlayer.isPlaying()) {
            lyricView.setPlayable(true);
            handler.sendEmptyMessageDelayed(Constant.MSG_REFRESH, 120);
            iv_play.setImageResource(R.mipmap.m_icon_player_pause_normal);
            Logger.e("总时长" + musicPlayer.getDuration() + "当前播放时间为" + musicPlayer.getCurrentPosition());
        } else {
            Logger.e("未播放");
            lyricView.setPlayable(false);
        }

    }




    @OnClick({R.id.iv_pre, R.id.iv_play, R.id.iv_next,R.id.iv_setting})
    public void pre(View v) {
        if(press_animator != null && press_animator.isRunning()) {
            press_animator.cancel();
        }
        if(up_animator != null && up_animator.isRunning()) {
            up_animator.cancel();
        }

        switch (v.getId()) {
            case R.id.iv_pre:
                musicPlayer.previous();
                break;

            case R.id.iv_next:
                musicPlayer.next();
                break;

            case R.id.iv_play:
                PlayEvent playEvent = new PlayEvent();
                if (musicPlayer.isPlaying()) {
                    playEvent.setAction(PlayEvent.Action.STOP);
                    iv_play.setImageResource(R.mipmap.m_icon_player_play_normal);


                } else {
                    playEvent.setAction(PlayEvent.Action.RESUME);
                    iv_play.setImageResource(R.mipmap.m_icon_player_pause_normal);
                }
                RxBus.get().post(playEvent);
                break;

            case R.id.iv_setting:
                if(customRelativeLayout == null) {
                    customRelativeLayout = (CustomRelativeLayout) setting_layout.inflate();
                    initCustomSettingView();
                }
                customRelativeLayout.show();
                break;
        }
        press_animator = pressAnimator(v);
        press_animator.start();
    }

    private void initCustomSettingView() {
        customSettingView = (CustomSettingView) customRelativeLayout.getChildAt(0);
        customSettingView.setOnTextSizeChangeListener(new TextSizeChangeListener());
        customSettingView.setOnColorItemChangeListener(new ColorItemClickListener());
        customSettingView.setOnDismissBtnClickListener(new DismissBtnClickListener());
        customSettingView.setOnLineSpaceChangeListener(new LineSpaceChangeListener());
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.MSG_REFRESH:
                    if (musicPlayer!=null) {
                        lyricView.setCurrentTimeMillis(musicPlayer.getCurrentPosition());
                        seekBar.setProgress(musicPlayer.getCurrentPosition());
                        tv_crr_time.setText(MediaUtil.formaTime(musicPlayer.getCurrentPosition()));
                        handler.sendEmptyMessageDelayed(Constant.MSG_REFRESH, 120);
                    }
                    break;
            }
        }
    };

    @Override
    public void onPlayerClicked(long progress, String content) {
        if(musicPlayer != null && (musicPlayer.isPlaying() || musicPlayer.getPlayStatus() == ManagedMediaPlayer.Status.PAUSED)) {
            musicPlayer.seekTo((int) progress);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser)
            tv_crr_time.setText(MediaUtil.formaTime(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        handler.removeMessages(Constant.MSG_REFRESH);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        musicPlayer.seekTo(seekBar.getProgress());
        handler.sendEmptyMessageDelayed(Constant.MSG_REFRESH, 120);
    }


    private class TextSizeChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser) {
                lyricView.setTextSize(15.0f + 3 * progress / 100.0f);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            RxSharedPreferences rxSharedPreferences = RxSharedPreferences.create(defaultSharedPreferences);
            Preference<Float> aFloat = rxSharedPreferences.getFloat(Constant.KEY_TEXT_SIZE, 15.0f);
            aFloat.set(15.0f + 3 * seekBar.getProgress() / 100.0f);
        }
    }

    private class LineSpaceChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser) {
                lyricView.setLineSpace(12.0f + 3 * progress / 100.0f);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            RxSharedPreferences rxSharedPreferences = RxSharedPreferences.create(defaultSharedPreferences);
            Preference<Float> aFloat = rxSharedPreferences.getFloat(Constant.KEY_LINE_SPACE, 12.0f);
            aFloat.set(12.0f + 3 * seekBar.getProgress() / 100.0f);
        }
    }

    private class DismissBtnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if(customRelativeLayout != null) {
                customRelativeLayout.dismiss();
            }
        }
    }

    private class ColorItemClickListener implements CustomSettingView.OnColorItemChangeListener {

        @Override
        public void onColorChanged(int color) {
            lyricView.setHighLightTextColor(color);
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            RxSharedPreferences rxSharedPreferences = RxSharedPreferences.create(defaultSharedPreferences);
            Preference<Integer> integer = rxSharedPreferences.getInteger(Constant.KEY_HIGHLIGHT_COLOR, Color.parseColor("#4FC5C7"));
            integer.set(color);
            if(customRelativeLayout != null) {
                customRelativeLayout.dismiss();
            }
        }
    }



    public ValueAnimator pressAnimator(final View view) {
        final float size = view.getScaleX();
        ValueAnimator animator = ValueAnimator.ofFloat(size, size * 0.7f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewHelper.setScaleX(view, (Float) animation.getAnimatedValue());
                ViewHelper.setScaleY(view, (Float) animation.getAnimatedValue());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewHelper.setScaleX(view, size * 0.7f);
                ViewHelper.setScaleY(view, size * 0.7f);
                up_animator = upAnimator(view);
                up_animator.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                ViewHelper.setScaleX(view, size * 0.7f);
                ViewHelper.setScaleY(view, size * 0.7f);
            }
        });
        animator.setDuration(animatorDuration);
        return animator;
    }

    public ValueAnimator upAnimator(final View view) {
        final float size = view.getScaleX();
        ValueAnimator animator = ValueAnimator.ofFloat(size, size * 10 / 7.00f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewHelper.setScaleX(view, (Float) animation.getAnimatedValue());
                ViewHelper.setScaleY(view, (Float) animation.getAnimatedValue());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewHelper.setScaleX(view, size * 10 / 7.00f);
                ViewHelper.setScaleY(view, size * 10 / 7.00f);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                ViewHelper.setScaleX(view, size * 10 / 7.00f);
                ViewHelper.setScaleY(view, size * 10 / 7.00f);
            }
        });
        animator.setDuration(animatorDuration);
        return animator;
    }


    @Subscribe
    public void reflashView(ReflashViewEvent event) {
        initMpv();
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        tb_music.setNavigationIcon(R.mipmap.ic_arrow_back_white_36dp);
        tb_music.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchView.getMeasuredWidth() > searchWidth) {
                    searchView.onActionViewCollapsed();
                } else {
                    finish();
                }

            }
        });

        tb_music.inflateMenu(R.menu.music_menu);
        tb_music.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_more:
                        viewDelegate.snackbar("更多被点击");
                        break;
                }
                return false;
            }
        });
        searchView = (SearchView) tb_music.findViewById(R.id.toolbar_serach);
        searchWidth = searchView.getMeasuredWidth();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewDelegate.snackbar("提交的数据" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewDelegate.snackbar("新的文本" + newText);
                return false;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {//MENU键

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}

package com.sujian.materaildesign.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.constant.Constant;
import com.sujian.materaildesign.delegate.MusicDalegale;
import com.sujian.materaildesign.frame.presenter.ActivityPresenter;
import com.sujian.materaildesign.model.music.Song;
import com.sujian.materaildesign.player.MusicPlayer;
import com.sujian.materaildesign.player.PlayEvent;
import com.sujian.materaildesign.player.ReflashViewEvent;
import com.sujian.materaildesign.uitls.MediaUtil;
import com.sujian.materaildesign.widget.LyricView;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * 音乐presenter
 */
public class MusicActivity extends ActivityPresenter<MusicDalegale> {

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
            Logger.e("总时长" + musicPlayer.getDuration() + "当前播放时间为" + musicPlayer.getCurrentPosition());
        } else {
            Logger.e("未播放");
            lyricView.setPlayable(false);
        }

    }




    @OnClick({R.id.iv_pre, R.id.iv_play, R.id.iv_next})
    public void pre(View v) {
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

                } else {
                    playEvent.setAction(PlayEvent.Action.RESUME);
                }
                RxBus.get().post(playEvent);
                break;
        }

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

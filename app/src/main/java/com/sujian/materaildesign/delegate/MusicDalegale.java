package com.sujian.materaildesign.delegate;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.constant.Constant;
import com.sujian.materaildesign.frame.view.AppDelegate;
import com.sujian.materaildesign.presenter.MusicActivity;
import com.sujian.materaildesign.rxkit.SchedulersCompat;
import com.sujian.materaildesign.rxkit.subscribe.GlideBlurOnSubscribe;
import com.sujian.materaildesign.widget.LyricView;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;

/**
 * 音乐view
 * Created by sujian on 2016/6/3.
 * Mail:121116111@qq.com
 */
public class MusicDalegale extends AppDelegate {
    @BindView(R.id.rl_music)
    RelativeLayout rl_music;

    @BindView(R.id.tb_music)
    Toolbar tb_music;

    @BindView(R.id.lyricview)
    LyricView lyricView;

    private MusicActivity musicActivity;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_music;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        RxSharedPreferences rxSharedPreferences = RxSharedPreferences.create(defaultSharedPreferences);
        Preference<Float> sizeFloat = rxSharedPreferences.getFloat(Constant.KEY_TEXT_SIZE, 12.0f);
        Preference<Float> spaceFloat = rxSharedPreferences.getFloat(Constant.KEY_LINE_SPACE, 15.0f);
        Preference<Integer> integer = rxSharedPreferences.getInteger(Constant.KEY_HIGHLIGHT_COLOR, Color.parseColor("#4FC5C7"));
        lyricView.setLineSpace(sizeFloat.get());
        lyricView.setTextSize(spaceFloat.get());
        lyricView.setHighLightTextColor(integer.get());
    }



    @Override
    public int getOptionsMenuId() {
        return R.menu.music_menu;
    }


    /**
     * 通过url得到图片并进行磨砂
     * @param url
     */
    public void initBG(String url) {
        Observable.create(new GlideBlurOnSubscribe(getActivity(), 100, url))
                .compose(SchedulersCompat.<GlideDrawable>observeOnMainThread())
                .subscribe(new Subscriber<GlideDrawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onNext(GlideDrawable glideDrawable) {
                        Logger.e("成功得到磨砂图片");
                        rl_music.setBackground(glideDrawable);

                    }
                });
    }
}

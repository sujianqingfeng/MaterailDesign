package com.sujian.materaildesign.delegate;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.R;
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
    LinearLayout rl_music;

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
        lyricView.setLineSpace(12.0f);
        lyricView.setTextSize(15.0f);
        lyricView.setHighLightTextColor(Color.parseColor("#4FC5C7"));
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

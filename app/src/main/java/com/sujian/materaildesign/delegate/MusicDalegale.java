package com.sujian.materaildesign.delegate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.frame.view.AppDelegate;
import com.sujian.materaildesign.presenter.MusicActivity;

import net.qiujuer.genius.blur.StackBlur;

import butterknife.BindView;

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

    private MusicActivity musicActivity;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_music;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initBG();
    }

    @Override
    public Toolbar getToolbar() {
        tb_music.setTitle("年轻的战场");
        return super.getToolbar();
    }


    @Override
    public int getOptionsMenuId() {
        return R.menu.music_menu;
    }


    private void initBG() {
        rl_music.setBackgroundResource(R.mipmap.iv_splash);
        final Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.iv_splash);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap b = StackBlur.blurNatively(bitmap, 50, false);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rl_music.setBackgroundDrawable(new BitmapDrawable(getActivity().getResources(), b));
                    }
                });
            }
        }).start();


    }
}

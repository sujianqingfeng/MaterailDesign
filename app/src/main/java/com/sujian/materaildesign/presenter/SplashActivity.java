package com.sujian.materaildesign.presenter;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.Bundle;
import android.util.Log;

import com.sujian.materaildesign.delegate.SplashDelegate;
import com.sujian.materaildesign.frame.presenter.ActivityPresenter;

/**
 * 启动页的Presenter
 */
public class SplashActivity extends ActivityPresenter<SplashDelegate> {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                    break;
            }
        }
    };

    private long TIME = 3000;


    @Override
    protected Class<SplashDelegate> getDelegateClass() {
        return SplashDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        handler.sendEmptyMessageDelayed(1, TIME);
    }
}

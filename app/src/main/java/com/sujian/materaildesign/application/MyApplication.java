package com.sujian.materaildesign.application;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDelegate;

import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.constant.Constant;

import rx.functions.Action1;

/**
 * application
 * Created by sujian on 2016/5/31.
 * Mail:121116111@qq.com
 */
public class MyApplication extends Application {


    static {


    }

    @Override
    public void onCreate() {

        super.onCreate();
        initTheme();
        //初始化log
        Logger
                .init("SuJian Materail Design") // default PRETTYLOGGER or use just init()
                .methodCount(0)                 // default 2
                //.hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2)                // default 0
                .logTool(new AndroidLogTool());
    }


    /**
     * 初始化主题
     */
    private void initTheme() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        RxSharedPreferences rxPreferences = RxSharedPreferences.create(preferences);
        Preference<Boolean> isNight = rxPreferences.getBoolean(Constant.ISNIGHT, false);
        isNight.asObservable()
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                        } else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                        }
                    }
                });

    }
}

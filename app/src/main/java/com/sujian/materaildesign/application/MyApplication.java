package com.sujian.materaildesign.application;

import android.app.Application;

import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * application
 * Created by sujian on 2016/5/31.
 * Mail:121116111@qq.com
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化log
        Logger
                .init("SuJian Materail Design") // default PRETTYLOGGER or use just init()
                .methodCount(0)                 // default 2
                //.hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2)                // default 0
                .logTool(new AndroidLogTool());
    }
}

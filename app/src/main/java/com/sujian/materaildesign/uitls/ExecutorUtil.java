package com.sujian.materaildesign.uitls;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by sujian on 2016/8/6.
 * Mail:121116111@qq.com
 */
public class ExecutorUtil {

    private static class IoExector implements Executor {
        @Override
        public void execute(final Runnable command) {
            Schedulers.io()
                    .createWorker()
                    .schedule(new Action0() {
                        @Override
                        public void call() {
                            command.run();
                        }
                    });
        }
    }


    private static class SingleThreadExecutor implements Executor {
        @Override
        public void execute(@NonNull final Runnable runnable) {
            Executors.newSingleThreadExecutor().execute(runnable);
        }
    }

    public static Executor getIoExector() {
        return new IoExector();
    }

    public static Executor getSingleThreadExecutor() {
        return new SingleThreadExecutor();
    }
}

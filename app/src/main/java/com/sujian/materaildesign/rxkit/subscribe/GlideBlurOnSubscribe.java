package com.sujian.materaildesign.rxkit.subscribe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.jakewharton.rxbinding.internal.Preconditions;
import com.sujian.materaildesign.rxkit.GlideError;
import com.sujian.materaildesign.rxkit.GlideTransformation;
import com.sujian.materaildesign.uitls.ExecutorUtil;

import net.qiujuer.genius.blur.StackBlur;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by sujian on 2016/8/6.
 * Mail:121116111@qq.com
 */
public class GlideBlurOnSubscribe implements Observable.OnSubscribe<GlideDrawable> {

    private String url;
    private int radius;
    private Context context;
    private Executor cacheExecutor;
    private MyFutureTask futureTask = null;

    private Handler mainHandler = new Handler(Looper.getMainLooper());

    public GlideBlurOnSubscribe(Context context, int radius, String url) {
        this.context = context;
        this.radius = radius;
        this.url = url;
        this.cacheExecutor = ExecutorUtil.getSingleThreadExecutor();
    }

    @Override
    public void call(final Subscriber<? super GlideDrawable> subscriber) {

        Preconditions.checkUiThread();

        if (!subscriber.isUnsubscribed()) {
            futureTask = new MyFutureTask(new MyCallback(), subscriber);
            cacheExecutor.execute(futureTask);
            subscriber.add(Subscriptions.from(futureTask));
        }

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {

                if (futureTask != null) {
                    futureTask.callback.getFutureTarget().clear();
                }

                mainHandler.removeCallbacksAndMessages(null);
                GlideBlurOnSubscribe.this.context = null;
            }
        });
    }


    class MyCallback implements Callable<GlideDrawable> {

        private FutureTarget<GlideDrawable> futureTarget;

        public FutureTarget<GlideDrawable> getFutureTarget() {
            return futureTarget;
        }

        @Override
        public GlideDrawable call() throws Exception {

            futureTarget = Glide
                    .with(context)
                    .load(url)
                    .bitmapTransform(new GlideTransformation(context, Glide.get(context).getBitmapPool(), radius))
                    .into(-1, -1);

            return futureTarget.get();
        }
    }


    class MyFutureTask extends FutureTask<GlideDrawable> {

        private MyCallback callback;
        private Subscriber<? super GlideDrawable> subscriber;

        public MyFutureTask(Callable<GlideDrawable> callable, Subscriber<? super GlideDrawable> subscriber) {
            super(callable);
            this.callback = (MyCallback) callable;
            this.subscriber = subscriber;
        }

        @Override
        protected void done() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (isDone() && !isCancelled()) {
                            subscriber.onNext(MyFutureTask.this.get());
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        subscriber.onError(e);
                    }
                }
            });
        }
    }
}

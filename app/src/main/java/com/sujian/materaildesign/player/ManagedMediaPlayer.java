package com.sujian.materaildesign.player;

import android.media.MediaPlayer;

import com.orhanobut.logger.Logger;

import java.io.IOException;

/**
 * 拓展MediaPlayer的功能 主要增加状态
 * Created by sujian on 2016/7/16.
 * Mail:121116111@qq.com
 */
public class ManagedMediaPlayer extends MediaPlayer implements MediaPlayer.OnCompletionListener {
    //播放状态
    private Status mState;
    //完成监听器
    private OnCompletionListener mOnCompletionListener;

    //枚举状态 闲置 初始化完成 开始 暂停 完成
    public enum Status {
        IDLE, INITIAIZED, STARTED, PAUSED, STOPPED, COMPLETED
    }

    //初始化
    public ManagedMediaPlayer() {
        super();
        mState = Status.IDLE;
        super.setOnCompletionListener(this);
    }

    //设置资源路径
    @Override
    public void setDataSource(String path) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        super.setDataSource(path);
        mState = Status.INITIAIZED;
    }

    //开始
    @Override
    public void start() throws IllegalStateException {
        super.start();
        mState = Status.STARTED;
        Logger.e("播放了");
    }

    //暂停
    @Override
    public void pause() throws IllegalStateException {
        super.pause();
        mState = Status.PAUSED;
    }

    //停止
    @Override
    public void stop() throws IllegalStateException {
        super.stop();
        mState = Status.STOPPED;
    }

    //设置完成监听器
    public void setmOnCompletionListener(OnCompletionListener mOnCompletionListener) {
        this.mOnCompletionListener = mOnCompletionListener;
    }

    //执行传过来的oncopletion方法
    @Override
    public void onCompletion(MediaPlayer mp) {
        mState = Status.COMPLETED;
        if (mOnCompletionListener != null) {
            mOnCompletionListener.onCompletion(mp);
        }
    }

    //得到当前状态
    public Status getmState() {
        return mState;
    }

    //是否播放完成
    public boolean isCompletion() {
        return mState == Status.COMPLETED;
    }


}

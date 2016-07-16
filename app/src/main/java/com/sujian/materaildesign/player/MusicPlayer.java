package com.sujian.materaildesign.player;

import android.media.MediaPlayer;

import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.model.music.Song;
import com.sujian.materaildesign.uitls.T;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 播放控制封装 播放队列
 * Created by sujian on 2016/7/16.
 * Mail:121116111@qq.com
 */
public class MusicPlayer implements MediaPlayer.OnCompletionListener {

    private static MusicPlayer musicPlayer = new MusicPlayer();
    private List<Song> mQueue;
    private int mQueueIndex;
    private ManagedMediaPlayer managedMediaPlayer;
    private PlayerMode playerMode;


    //枚举 播放模式 循环 随机 重复
    private enum PlayerMode {
        LOOP, RANDOM, REPEAT
    }

    //初始化
    public MusicPlayer() {
        mQueue = new ArrayList<>();
        mQueueIndex = 0;
        managedMediaPlayer = new ManagedMediaPlayer();
        managedMediaPlayer.setmOnCompletionListener(this);
        playerMode = PlayerMode.LOOP;
    }

    /**
     * 设置歌曲队列并播放
     *
     * @param queue
     * @param index
     */
    public void setQueue(List<Song> queue, int index) {
        mQueue = queue;
        mQueueIndex = index;
        play(getNowPlaying());
    }


    /**
     * 暂停
     */
    public void pause() {
        managedMediaPlayer.pause();
    }

    /**
     * 开始
     */
    public void resume() {
        managedMediaPlayer.start();
    }

    /**
     * 下一首
     */
    public void next() {
        play(getNextSong());
    }


    /**
     * 上一首
     */
    public void previous() {
        play(getPreviousSong());
    }


    /**
     * 播放
     *
     * @param song
     */
    public void play(Song song) {
        Logger.e("播放方法调用 url为" + song.getPath());
        try {
            managedMediaPlayer.reset();
            managedMediaPlayer.setDataSource(song.getPath());
            managedMediaPlayer.prepareAsync();
            managedMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Logger.e("准备完毕");
                    managedMediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 得到当前播放的歌曲
     *
     * @return
     */
    public Song getNowPlaying() {
        if (mQueue == null)
            return null;
        return mQueue.get(mQueueIndex);
    }

    //得到下一首
    private Song getNextSong() {
        if (mQueue == null) {
            return null;
        }

        switch (playerMode) {
            case LOOP:
                return mQueue.get(getNextIndex());
            case RANDOM:
                return mQueue.get(getRandomIndex());
            case REPEAT:
                return mQueue.get(mQueueIndex);

        }

        return null;
    }


    //得到上一首
    private Song getPreviousSong() {
        if (mQueue == null) {
            return null;
        }

        switch (playerMode) {
            case LOOP:
                return mQueue.get(getPreviousIndex());
            case RANDOM:
                return mQueue.get(getRandomIndex());
            case REPEAT:
                return mQueue.get(mQueueIndex);

        }

        return null;
    }

    /**
     * 得到当前播放的位置
     *
     * @return
     */
    public int getCurrentPosition() {
        if (getNowPlaying() != null) {
            return managedMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    /**
     * 得到时长
     *
     * @return
     */
    public int getDuration() {
        if (getNowPlaying() != null) {
            return managedMediaPlayer.getDuration();
        }
        return 0;
    }


    //得到下一首的索引
    private int getNextIndex() {
        mQueueIndex = (mQueueIndex + 1) % mQueue.size();
        return mQueueIndex;
    }

    //得到上一首的索引
    private int getPreviousIndex() {
        mQueueIndex = (mQueueIndex - 1) % mQueue.size();
        return mQueueIndex;
    }

    //得到随机索引
    private int getRandomIndex() {
        mQueueIndex = new Random().nextInt(mQueue.size()) % mQueue.size();
        return mQueueIndex;
    }


    //播放完成
    @Override
    public void onCompletion(MediaPlayer mp) {
        next();
    }

    /**
     * 释放资源
     */
    public void release() {
        managedMediaPlayer.release();
        managedMediaPlayer = null;
    }

    public static MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public static void setMusicPlayer(MusicPlayer musicPlayer) {
        MusicPlayer.musicPlayer = musicPlayer;
    }

    public PlayerMode getPlayerMode() {
        return playerMode;
    }

    public void setPlayerMode(PlayerMode playerMode) {
        this.playerMode = playerMode;
    }
}

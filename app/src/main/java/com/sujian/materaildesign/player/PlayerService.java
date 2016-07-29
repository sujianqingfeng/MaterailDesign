package com.sujian.materaildesign.player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.orhanobut.logger.Logger;

/**
 * Created by sujian on 2016/7/16.
 * Mail:121116111@qq.com
 */
public class PlayerService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RxBus.get().register(this);
        Logger.e("事件注册");
    }

    @Subscribe
    public void event(PlayEvent playEvent) {
        MusicPlayer musicPlayer = MusicPlayer.getMusicPlayer();
        switch (playEvent.getAction()) {
            case PLAY:
                Logger.e("开始播放 seekto" + playEvent.getSeekTo());
                musicPlayer.setQueue(playEvent.getQueue(), playEvent.getSeekTo());
                break;
            case NEXT:
                musicPlayer.next();
                break;

            case STOP:
                musicPlayer.pause();
                break;
            case RESUME:
                musicPlayer.resume();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
        Logger.e("事件销毁");
    }
}

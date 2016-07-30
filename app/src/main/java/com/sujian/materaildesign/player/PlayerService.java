package com.sujian.materaildesign.player;

import android.app.Service;
import android.content.Intent;
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
    }

    @Subscribe
    public void onEvent(PlayEvent playEvent) {
        Logger.e("接收到playevent");
        MusicPlayer musicPlayer = MusicPlayer.getMusicPlayer();
        switch (playEvent.getAction()) {
            case PLAY:
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
    }
}

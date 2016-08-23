package com.sujian.materaildesign.player;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;


import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.orhanobut.logger.Logger;


/**
 * 开启播放的服务
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
        setPlayModel();
    }

    /**
     * 设置播放的模式
     */
    private void setPlayModel() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getApplication());
        RxSharedPreferences rxSharedPreferences = RxSharedPreferences.create(defaultSharedPreferences);
        Preference<MusicPlayer.PlayerMode> playmodel = rxSharedPreferences.getEnum("playmodel", MusicPlayer.PlayerMode.class);
        MusicPlayer.PlayerMode playerMode = playmodel.get();
        if (playerMode==null)
            return;
        Logger.e("播放的模式为"+playerMode.toString());
    }

    @Subscribe
    public void onEvent(PlayEvent playEvent) {
        Logger.e("接收到playevent");
        MusicPlayer musicPlayer = MusicPlayer.getMusicPlayer();
        switch (playEvent.getAction()) {
            case PLAY:
                if (playEvent.getQueue()!=null)
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

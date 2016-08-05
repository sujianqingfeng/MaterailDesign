package com.sujian.materaildesign.model.music;

import com.sujian.materaildesign.constant.Constant;
import com.sujian.materaildesign.uitls.RetrofitWapper;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 网络音乐
 * Created by sujian on 2016/7/29.
 * Mail:121116111@qq.com
 */
public class NetworkMusicModel implements INetworkMusicModel {
    @Override
    public void getLinkSongList(int type, int size, int page, Subscriber<LinkSongList> subscriber) {
        NetworkMusicApi networkMusicApi = RetrofitWapper.getRetrofitWapperInstance()
                .setBaseUrl(Constant.MUSIC_BASE_URL)
                .create(NetworkMusicApi.class);
        networkMusicApi.getLinkSongList(type, size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}

package com.sujian.materaildesign.model.music;

import com.sujian.materaildesign.constant.Constant;
import com.sujian.materaildesign.uitls.RetrofitWapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by sujian on 2016/7/29.
 * Mail:121116111@qq.com
 */
public class BillboardModel implements IBillboardModel {
    Subscription subscribe;
    @Override
    public void getBillboard(Integer[] types, Subscriber<Billboard> subscriber) {
        final NetworkMusicApi networkMusicApi = RetrofitWapper.getRetrofitWapperInstance(Constant.MUSIC_BASE_URL).create(NetworkMusicApi.class);

        subscribe = Observable.from(types)
                .flatMap(new Func1<Integer, Observable<LinkSongList>>() {
                    @Override
                    public Observable<LinkSongList> call(Integer integer) {
                        return networkMusicApi.getLinkSongList(integer, 3, 0);
                    }
                })
                .flatMap(new Func1<LinkSongList, Observable<Billboard>>() {
                    @Override
                    public Observable<Billboard> call(LinkSongList linkSongList) {
                        Billboard b = new Billboard();
                        b.setBillboardBean(linkSongList.getBillboard());
                        b.setSongListBeen(linkSongList.getSong_list());
                        return Observable.just(b);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    public void unsubscribe() {
        subscribe.unsubscribe();
    }
}

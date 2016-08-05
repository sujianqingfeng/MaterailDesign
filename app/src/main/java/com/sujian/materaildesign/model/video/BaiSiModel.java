package com.sujian.materaildesign.model.video;

import com.sujian.materaildesign.constant.Constant;
import com.sujian.materaildesign.uitls.RetrofitWapper;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sujian on 2016/7/4.
 * Mail:121116111@qq.com
 */
public class BaiSiModel implements IBaiSiModel {

    private Subscription subscribe;

    @Override
    public void getBaiSiModel(int p, Subscriber<BaiSiEntity> subscriber) {
        BaiSiApi baiSiApi = RetrofitWapper.getRetrofitWapperInstance()
                .setBaseUrl(Constant.YIYUAN_BASE_URL)
                .create(BaiSiApi.class);
        subscribe = baiSiApi.getBaiSi(p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void unsubscribe() {
        subscribe.unsubscribe();
    }
}

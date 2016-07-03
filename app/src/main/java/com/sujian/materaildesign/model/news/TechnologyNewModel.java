package com.sujian.materaildesign.model.news;

import com.sujian.materaildesign.uitls.RetrofitWapper;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 数据来源 网络获取
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */
public class TechnologyNewModel implements ITechnologyNewModel {
    @Override
    public void getTechnologyNew(int n, int p, Subscriber<NewEntity> subscriber) {
        TechnologyNewApi technologyNewApi = RetrofitWapper.getRetrofitWapperInstance().create(TechnologyNewApi.class);
        technologyNewApi.getTechnologyNew(n, p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}

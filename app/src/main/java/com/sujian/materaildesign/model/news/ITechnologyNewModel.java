package com.sujian.materaildesign.model.news;

import rx.Observer;
import rx.Subscriber;

/**
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */
public interface ITechnologyNewModel {
    void getTechnologyNew(int n, int p, Subscriber<NewEntity> subscriber);

    void unsubscribe();
}

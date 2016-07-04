package com.sujian.materaildesign.model.video;

import rx.Subscriber;

/**
 * Created by sujian on 2016/7/4.
 * Mail:121116111@qq.com
 */
public interface IBaiSiModel {
    void getBaiSiModel(int p, Subscriber<BaiSiEntity> subscriber);
}

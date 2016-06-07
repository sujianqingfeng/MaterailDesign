package com.sujian.materaildesign.model.picture;

import rx.Subscriber;

/**
 * Created by sujian on 2016/6/2.
 * Mail:121116111@qq.com
 */
public interface IPictureModel {
    void getPicture(int num, Subscriber<PictureEntity> subscriber);
}

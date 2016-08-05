package com.sujian.materaildesign.model.picture;

import com.sujian.materaildesign.constant.Constant;
import com.sujian.materaildesign.uitls.RetrofitWapper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sujian on 2016/6/2.
 * Mail:121116111@qq.com
 */
public class PictureModel implements IPictureModel {
    @Override
    public void getPicture(int num, Subscriber<PictureEntity> subscriber) {
        PictureApi pictureApi = RetrofitWapper.getRetrofitWapperInstance()
                .setBaseUrl(Constant.BAIDU_BASE_URL)
                .create(PictureApi.class);
        pictureApi.getPicture(num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}

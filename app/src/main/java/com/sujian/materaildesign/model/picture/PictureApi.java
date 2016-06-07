package com.sujian.materaildesign.model.picture;

import com.sujian.materaildesign.model.picture.PictureEntity;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 访问图片的接口
 * Created by sujian on 2016/5/30.
 * Mail:121116111@qq.com
 */
public interface PictureApi {
    @GET("txapi/mvtp/meinv")
    @Headers("apikey:0857263486f4bb2376f5a37002b48bc4")
    Observable<PictureEntity> getPicture(@Query("num") int i);
}

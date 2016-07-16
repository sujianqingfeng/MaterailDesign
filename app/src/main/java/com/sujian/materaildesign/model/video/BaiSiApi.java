package com.sujian.materaildesign.model.video;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sujian on 2016/7/4.
 * Mail:121116111@qq.com
 */
public interface BaiSiApi {
    @GET("255-1?showapi_appid=21494&type=41&title=&showapi_sign=f0821c9f6822404492d07eb74fefeea0")
    Observable<BaiSiEntity> getBaiSi(@Query("page") int p);

}

package com.sujian.materaildesign.model.news;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 科技新闻api
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */
public interface TechnologyNewApi {
    @GET("txapi/keji/keji")
    @Headers("apikey:0857263486f4bb2376f5a37002b48bc4")
    Observable<NewEntity> getTechnologyNew(@Query("num") int n, @Query("page") int p);
}

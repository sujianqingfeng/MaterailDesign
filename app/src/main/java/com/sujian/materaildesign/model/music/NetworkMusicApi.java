package com.sujian.materaildesign.model.music;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 百度音乐接口
 * Created by sujian on 2016/7/29.
 * Mail:121116111@qq.com
 */
public interface NetworkMusicApi {
    @GET("v1/restserver/ting/?format=json&calback=&from=webapp_music&method==baidu.ting.billboard.billList")
    Observable<LinkSongList> getLinkSongList(@Query("type") int type, @Query("size") int size, @Query("offset") int page);
}


//format=json&calback=&from=webapp_music&method==baidu.ting.billboard.billList&type=1&size=10&offset=0
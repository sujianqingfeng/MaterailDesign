package com.sujian.materaildesign.model.music;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sujian on 2016/8/4.
 * Mail:121116111@qq.com
 */
public interface SingleNetworkMusicApi {
    @GET("v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.song.play")
    Observable<SongInfo> getSongInfo(@Query("songid") String songid);

}
//http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.song.play&songid=877578

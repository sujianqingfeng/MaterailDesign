package com.sujian.materaildesign.model.music;

import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.constant.Constant;
import com.sujian.materaildesign.uitls.RetrofitWapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 单曲信息接口实现类
 * Created by sujian on 2016/8/4.
 * Mail:121116111@qq.com
 */
public class SingleNetworkMusicModel implements ISingleNetworkMusicModel {
    @Override
    public void getSingleIfo(int type, int size, int page, Subscriber<List<Song>> subscriber) {
        final SingleNetworkMusicApi singleNetworkMusicApi = RetrofitWapper.getRetrofitWapperInstance()
                .setBaseUrl(Constant.MUSIC_BASE_URL)
                .create(SingleNetworkMusicApi.class);
        NetworkMusicApi networkMusicApi = RetrofitWapper.getRetrofitWapperInstance()
                .setBaseUrl(Constant.MUSIC_BASE_URL)
                .create(NetworkMusicApi.class);
        networkMusicApi.getLinkSongList(type, size, page)
                .flatMap(new Func1<LinkSongList, Observable<LinkSongList.SongListBean>>() {
                    @Override
                    public Observable<LinkSongList.SongListBean> call(LinkSongList linkSongList) {
                        return Observable.from(linkSongList.getSong_list());
                    }
                })
                .flatMap(new Func1<LinkSongList.SongListBean, Observable<String>>() {
                    @Override
                    public Observable<String> call(LinkSongList.SongListBean songListBean) {
                        return Observable.just(songListBean.getSong_id());
                    }
                })
                .flatMap(new Func1<String, Observable<SongInfo>>() {
                    @Override
                    public Observable<SongInfo> call(String s) {
                        return singleNetworkMusicApi.getSongInfo(s);
                    }
                })
                .flatMap(new Func1<SongInfo, Observable<Song>>() {
                    @Override
                    public Observable<Song> call(SongInfo songInfo) {
                        Song song = new Song();
                        song.setMusicType(Song.MusicType.NetworkMusic);
                        song.setPath(songInfo.getBitrate().getShow_link());
                        song.setAlbum(songInfo.getSonginfo().getAlbum_title());
                        song.setAlbumId(Long.parseLong(songInfo.getSonginfo().getAlbum_id()));
                        song.setArtist(songInfo.getSonginfo().getAuthor());
                        song.setSize(songInfo.getBitrate().getFile_size());
                        song.setDuration(songInfo.getBitrate().getFile_duration());
                        song.setTitle(songInfo.getSonginfo().getTitle());
                        song.setPicUrl(songInfo.getSonginfo().getPic_big());
                        return Observable.just(song);
                    }
                })
                .buffer(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }
}

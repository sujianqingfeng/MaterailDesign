package com.sujian.materaildesign.model.music;

import java.util.List;

import rx.Subscriber;

/**
 * 新歌榜模型数据
 * Created by sujian on 2016/7/29.
 * Mail:121116111@qq.com
 */
public interface INetworkMusicModel {
    void getLinkSongList(int type, int size, int page, Subscriber<LinkSongList> subscriber);
}

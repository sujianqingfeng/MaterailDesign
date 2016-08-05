package com.sujian.materaildesign.model.music;

import java.util.List;

import rx.Subscriber;

/**
 * 单曲网络接口模型
 * Created by sujian on 2016/8/4.
 * Mail:121116111@qq.com
 */
public interface ISingleNetworkMusicModel {
    void getSingleIfo(int type, int size, int page, Subscriber<List<Song>> subscriber);
}

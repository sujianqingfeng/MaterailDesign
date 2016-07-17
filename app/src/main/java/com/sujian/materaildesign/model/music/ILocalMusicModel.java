package com.sujian.materaildesign.model.music;

import android.content.Context;

import java.util.List;

/**
 * 本地音乐数据接口
 * Created by sujian on 2016/7/17.
 * Mail:121116111@qq.com
 */
public interface ILocalMusicModel {
    List<Song> getLocalMusicList(Context context);
}

package com.sujian.materaildesign.player;

import com.sujian.materaildesign.model.music.Song;

/**
 * Created by sujian on 2016/8/11.
 * Mail:121116111@qq.com
 */
public class ReflashViewEvent {
    private Song song;

    public ReflashViewEvent(Song song) {
        this.song = song;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}

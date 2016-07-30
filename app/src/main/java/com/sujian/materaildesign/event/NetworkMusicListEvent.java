package com.sujian.materaildesign.event;

/**
 * Created by sujian on 2016/7/30.
 * Mail:121116111@qq.com
 */
public class NetworkMusicListEvent {
    private int type;

    public NetworkMusicListEvent() {

    }

    public NetworkMusicListEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

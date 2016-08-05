package com.sujian.materaildesign.model.music;

import java.util.List;

/**
 * 转成通用的Song的榜单数据
 * Created by sujian on 2016/8/5.
 * Mail:121116111@qq.com
 */
public class SongList {
    private String billboardName;
    private String billboardPic;
    private List<Song> songList;


    public String getBillboardName() {
        return billboardName;
    }

    public void setBillboardName(String billboardName) {
        this.billboardName = billboardName;
    }

    public String getBillboardPic() {
        return billboardPic;
    }

    public void setBillboardPic(String billboardPic) {
        this.billboardPic = billboardPic;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    @Override
    public String toString() {
        return "SongList{" +
                "billboardName='" + billboardName + '\'' +
                ", billboardPic='" + billboardPic + '\'' +
                ", songList=" + songList +
                '}';
    }
}

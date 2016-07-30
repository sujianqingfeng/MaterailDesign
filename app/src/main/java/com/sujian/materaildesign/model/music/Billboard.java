package com.sujian.materaildesign.model.music;

import java.util.List;

/**
 * Created by sujian on 2016/7/29.
 * Mail:121116111@qq.com
 */
public class Billboard {
    private LinkSongList.BillboardBean billboardBean;
    private List<LinkSongList.SongListBean> songListBeen;

    public LinkSongList.BillboardBean getBillboardBean() {
        return billboardBean;
    }

    public void setBillboardBean(LinkSongList.BillboardBean billboardBean) {
        this.billboardBean = billboardBean;
    }

    public List<LinkSongList.SongListBean> getSongListBeen() {
        return songListBeen;
    }

    public void setSongListBeen(List<LinkSongList.SongListBean> songListBeen) {
        this.songListBeen = songListBeen;
    }

    @Override
    public String toString() {
        return "Billboard{" +
                "billboardBean=" + billboardBean +
                ", songListBeen=" + songListBeen +
                '}';
    }
}

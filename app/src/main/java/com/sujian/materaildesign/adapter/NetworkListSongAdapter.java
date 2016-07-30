package com.sujian.materaildesign.adapter;

import android.view.View;
import android.widget.TextView;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.model.music.LinkSongList;

import java.util.List;

import butterknife.BindView;

/**
 * 在线音乐adapter
 * Created by sujian on 2016/7/30.
 * Mail:121116111@qq.com
 */
public class NetworkListSongAdapter extends BaseRecylerViewAdapter<LinkSongList.SongListBean, NetworkMusicHolder> {

    public NetworkListSongAdapter(List<LinkSongList.SongListBean> list) {
        super(list);
    }

    @Override
    protected int setItemLayout() {
        return R.layout.localmusic_item_layout;
    }

    @Override
    protected RecyclerViewBaseHoler setViewHolder(View view) {
        return new NetworkMusicHolder(view);
    }

    @Override
    protected void refreshView() {
        viewHolder.title.setText(data.getTitle());
        viewHolder.songer.setText(data.getAuthor());
    }
}

class NetworkMusicHolder extends RecyclerViewBaseHoler {
    @BindView(R.id.tv_music_item_title)
    TextView title;
    @BindView(R.id.tv_music_item_songer)
    TextView songer;

    public NetworkMusicHolder(View itemView) {
        super(itemView);
    }
}
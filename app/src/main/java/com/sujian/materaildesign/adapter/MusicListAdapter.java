package com.sujian.materaildesign.adapter;

import android.view.View;
import android.widget.TextView;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.model.music.Song;

import java.util.List;

import butterknife.BindView;

/**
 * 音乐列表适配器
 * Created by sujian on 2016/7/17.
 * Mail:121116111@qq.com
 */
public class MusicListAdapter extends BaseRecylerViewAdapter<Song, MusicListHolder> {

    public MusicListAdapter(List<Song> list) {
        super(list);
    }

    @Override
    protected int setItemLayout() {
        return R.layout.music_item_layout;
    }

    @Override
    protected RecyclerViewBaseHoler setViewHolder(View view) {

        return new MusicListHolder(view);
    }

    @Override
    protected void refreshView() {
        viewHolder.title.setText(data.getTitle());
        viewHolder.songer.setText(data.getArtist());
    }


}


class MusicListHolder extends RecyclerViewBaseHoler {
    @BindView(R.id.tv_music_item_title)
    TextView title;
    @BindView(R.id.tv_music_item_songer)
    TextView songer;

    public MusicListHolder(View itemView) {
        super(itemView);
    }
}
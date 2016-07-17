package com.sujian.materaildesign.adapter;

import android.view.View;
import android.widget.TextView;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.model.music.Song;

import java.util.List;

import butterknife.BindView;

/**
 * Created by sujian on 2016/7/17.
 * Mail:121116111@qq.com
 */
public class LocalMusicAdapter extends BaseRecylerViewAdapter<Song, LocalMusicHolder> {

    public LocalMusicAdapter(List<Song> list) {
        super(list);
    }

    @Override
    protected int setItemLayout() {
        return R.layout.localmusic_item_layout;
    }

    @Override
    protected RecyclerViewBaseHoler setViewHolder(View view) {

        return new LocalMusicHolder(view);
    }

    @Override
    protected void refreshView() {
        viewHolder.title.setText(data.getTitle());
        viewHolder.songer.setText(data.getArtist());
    }


}


class LocalMusicHolder extends RecyclerViewBaseHoler {
    @BindView(R.id.tv_music_item_title)
    TextView title;
    @BindView(R.id.tv_music_item_songer)
    TextView songer;

    public LocalMusicHolder(View itemView) {
        super(itemView);
    }
}
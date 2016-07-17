package com.sujian.materaildesign.delegate;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.adapter.LocalMusicAdapter;
import com.sujian.materaildesign.adapter.NewRecyclerViewAdapter;
import com.sujian.materaildesign.frame.view.AppDelegate;
import com.sujian.materaildesign.model.music.Song;
import com.sujian.materaildesign.model.news.NewEntity;
import com.sujian.materaildesign.presenter.MusicListActivity;
import com.sujian.materaildesign.presenter.NewsActivity;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by sujian on 2016/7/17.
 * Mail:121116111@qq.com
 */
public class LocalMusicFDeletate extends AppDelegate {

    @BindView(R.id.rv_localmusic)
    RecyclerView rv_localmusic;

    private List<Song> list;
    private LocalMusicAdapter adapter;
    private MusicListActivity musicListActivity;
    private LinearLayoutManager linearLayoutManger;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_localmusic;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initRecyclerView();
    }


    private void initRecyclerView() {
        musicListActivity = getActivity();
        list = new ArrayList<>();

        rv_localmusic.setHasFixedSize(true);
        linearLayoutManger = new LinearLayoutManager(musicListActivity);
        linearLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);

        rv_localmusic.setLayoutManager(linearLayoutManger);
        adapter = new LocalMusicAdapter(list);
        rv_localmusic.setAdapter(adapter);
        rv_localmusic.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(musicListActivity)
                        .color(R.color.divider)
                        .sizeResId(R.dimen.divider)
                        .marginResId(R.dimen.leftmargin, R.dimen.rightmargin)
                        .build());

    }


    public void refreshRecyclerView(List<Song> data) {
        list.addAll(data);

        adapter.notifyDataSetChanged();

    }

    public LocalMusicAdapter getAdapter() {
        return adapter;
    }

    public List<Song> getList() {
        return list;
    }
}

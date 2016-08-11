package com.sujian.materaildesign.delegate;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sujian.materaildesign.R;

import com.sujian.materaildesign.adapter.MusicListAdapter;
import com.sujian.materaildesign.frame.view.AppDelegate;
import com.sujian.materaildesign.model.music.Song;
import com.sujian.materaildesign.presenter.MusicListActivity;
import com.sujian.materaildesign.widget.SpaceItemDecoration;

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
    private MusicListAdapter adapter;
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
        adapter = new MusicListAdapter(list);
        rv_localmusic.setAdapter(adapter);
        rv_localmusic.addItemDecoration(new SpaceItemDecoration(12));


    }


    public void refreshRecyclerView(List<Song> data) {
        list.addAll(data);

        adapter.notifyDataSetChanged();

    }

    public MusicListAdapter getAdapter() {
        return adapter;
    }

    public List<Song> getList() {
        return list;
    }
}

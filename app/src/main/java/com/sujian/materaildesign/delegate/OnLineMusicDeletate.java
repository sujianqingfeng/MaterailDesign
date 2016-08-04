package com.sujian.materaildesign.delegate;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.adapter.BillboardAdapter;
import com.sujian.materaildesign.frame.view.AppDelegate;
import com.sujian.materaildesign.model.music.Billboard;
import com.sujian.materaildesign.presenter.MusicListActivity;
import com.sujian.materaildesign.wiget.SpaceItemDecoration;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 在线view
 * Created by sujian on 2016/7/17.
 * Mail:121116111@qq.com
 */
public class OnLineMusicDeletate extends AppDelegate {


    private MusicListActivity activity;
    private LinearLayoutManager linearManager;
    private BillboardAdapter adapter;
    private List<Billboard> list;

    @BindView(R.id.rv_billboard)
    RecyclerView rv_billboard;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_onlinemusic;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initRecycler();
    }

    /**
     * 初始化榜单
     */
    private void initRecycler() {
        activity = getActivity();
        list = new ArrayList<>();
        rv_billboard.setHasFixedSize(true);
        linearManager = new LinearLayoutManager(activity);
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_billboard.setLayoutManager(linearManager);

        adapter = new BillboardAdapter(list, activity);
        rv_billboard.setAdapter(adapter);
        rv_billboard.addItemDecoration(new SpaceItemDecoration(12));


    }


    public void refreshRecyclerView(Billboard data) {
        list.add(data);

        adapter.notifyDataSetChanged();

    }


    public BillboardAdapter getAdapter() {
        return adapter;
    }

    public List<Billboard> getList() {
        return list;
    }
}

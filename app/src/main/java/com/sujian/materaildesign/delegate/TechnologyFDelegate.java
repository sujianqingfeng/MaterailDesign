package com.sujian.materaildesign.delegate;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.adapter.NewRecyclerViewAdapter;
import com.sujian.materaildesign.frame.view.AppDelegate;
import com.sujian.materaildesign.model.news.NewEntity;
import com.sujian.materaildesign.presenter.NewsActivity;
import com.sujian.materaildesign.wiget.SpaceItemDecoration;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 科技view
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */
public class TechnologyFDelegate extends AppDelegate {

    @BindView(R.id.rv_technology)
    RecyclerView rv_technology;

    @BindView(R.id.srl_technology)
    SwipeRefreshLayout srl_technology;

    private List<NewEntity.NewsList> list;
    private NewRecyclerViewAdapter adapter;
    private NewsActivity newsActivity;
    private LinearLayoutManager linearLayoutManger;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_technology;
    }


    @Override
    public void initWidget() {
        super.initWidget();
        initRecyclerView();
    }


    private void initRecyclerView() {
        newsActivity = getActivity();
        list = new ArrayList<>();

        rv_technology.setHasFixedSize(true);
        linearLayoutManger = new LinearLayoutManager(newsActivity);
        linearLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);

        rv_technology.setLayoutManager(linearLayoutManger);
        adapter = new NewRecyclerViewAdapter(newsActivity, list);
        rv_technology.setAdapter(adapter);
        rv_technology.addItemDecoration(new SpaceItemDecoration(12));


        srl_technology.setRefreshing(true);
    }


    public void refreshRecyclerView(List<NewEntity.NewsList> data, Boolean isLoadMore) {
        if (isLoadMore)
            list.addAll(data);
        else
            list.addAll(0, data);

        adapter.notifyDataSetChanged();
        srl_technology.setRefreshing(false);
    }

    public LinearLayoutManager getLinearLayoutManger() {
        return linearLayoutManger;
    }

    public NewRecyclerViewAdapter getAdapter() {
        return adapter;
    }

    public List<NewEntity.NewsList> getList() {
        return list;
    }
}

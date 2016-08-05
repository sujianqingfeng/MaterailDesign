package com.sujian.materaildesign.delegate;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.adapter.NewRecyclerViewAdapter;
import com.sujian.materaildesign.adapter.VideoRecyclerViewAdapter;
import com.sujian.materaildesign.frame.view.AppDelegate;
import com.sujian.materaildesign.model.news.NewEntity;
import com.sujian.materaildesign.model.video.BaiSiEntity;
import com.sujian.materaildesign.presenter.NewsActivity;
import com.sujian.materaildesign.presenter.VideoActivity;
import com.sujian.materaildesign.uitls.UIUitls;
import com.sujian.materaildesign.wiget.SpaceItemDecoration;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 公共view
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */
public class PublicVideoFDelegate extends AppDelegate {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<BaiSiEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list;
    private VideoRecyclerViewAdapter adapter;
    private VideoActivity videoActivity;
    private LinearLayoutManager linearLayoutManger;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_public;
    }


    @Override
    public void initWidget() {
        super.initWidget();
        initRecyclerView();
    }


    private void initRecyclerView() {
        videoActivity = getActivity();
        list = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        linearLayoutManger = new LinearLayoutManager(videoActivity);
        linearLayoutManger.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManger);
        adapter = new VideoRecyclerViewAdapter(videoActivity, list);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(12));


//        recyclerView.addItemDecoration(
//                new HorizontalDividerItemDecoration.Builder(videoActivity)
//                        .color(R.color.divider)
//                        .sizeResId(R.dimen.divider)
//                        .marginResId(R.dimen.leftmargin, R.dimen.rightmargin)
//                        .build());

        swipeRefreshLayout.setProgressViewOffset(false, 0, UIUitls.dip2px(getActivity(), 24));
        swipeRefreshLayout.setRefreshing(true);
    }


    public void refreshRecyclerView(List<BaiSiEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data, Boolean isLoadMore) {
        if (isLoadMore)
            list.addAll(data);
        else
            list.addAll(0, data);

        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    public LinearLayoutManager getLinearLayoutManger() {
        return linearLayoutManger;
    }

    public VideoRecyclerViewAdapter getAdapter() {
        return adapter;
    }

    public List<BaiSiEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean> getList() {
        return list;
    }
}

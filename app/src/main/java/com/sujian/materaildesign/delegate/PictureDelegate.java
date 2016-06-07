package com.sujian.materaildesign.delegate;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;


import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.adapter.PictureAdapter;
import com.sujian.materaildesign.frame.view.AppDelegate;
import com.sujian.materaildesign.model.picture.PictureEntity;
import com.sujian.materaildesign.presenter.PictureActivity;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by sujian on 2016/5/30.
 * Mail:121116111@qq.com
 */

public class PictureDelegate extends AppDelegate {

    @BindView(R.id.rv_picture)
    XRecyclerView rv_picture;

    @BindView(R.id.tb_picture)
    Toolbar tb_picture;

    List<PictureEntity.PictureListItem> list;

    boolean isLoadMore;

    StaggeredGridLayoutManager staggeredGridLayoutManager;

    PictureAdapter pictureAdapter;
    PictureActivity pictureActivity;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_picture;
    }


    @Override
    public Toolbar getToolbar() {
        tb_picture.setTitle("RecycleView 瀑布流");
        return get(R.id.tb_picture);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initView();
    }


    /**
     * 初始化控件
     */
    private void initView() {
        pictureActivity = getActivity();


        list = new ArrayList<>();

        rv_picture.setHasFixedSize(true);
        rv_picture.setLoadingMoreEnabled(true);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv_picture.setLayoutManager(staggeredGridLayoutManager);


        pictureAdapter = new PictureAdapter(getActivity(), list);
        rv_picture.setAdapter(pictureAdapter);


        rv_picture.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                pictureActivity.getDataFromService();
            }

            @Override
            public void onLoadMore() {
                isLoadMore = true;
                pictureActivity.getDataFromService();
            }
        });

        rv_picture.setRefreshProgressStyle(ProgressStyle.BallBeat);
        rv_picture.setLoadingMoreProgressStyle(ProgressStyle.Pacman);

    }


    /**
     * 刷新view
     *
     * @param newslist
     */
    public void refreshView(List<PictureEntity.PictureListItem> newslist) {
        if (isLoadMore) {
            list.addAll(newslist);
            pictureAdapter.notifyDataSetChanged();
            rv_picture.loadMoreComplete();
        } else {
            list.addAll(0, newslist);
            pictureAdapter.notifyDataSetChanged();
            pictureAdapter.notifyItemRemoved(pictureAdapter.getItemCount());
            rv_picture.refreshComplete();
        }
    }


    public PictureAdapter getPictureAdapter() {
        return pictureAdapter;
    }

    public List<PictureEntity.PictureListItem> getList() {
        return list;
    }

}

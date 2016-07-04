package com.sujian.materaildesign.presenter;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.adapter.NewRecyclerViewAdapter;
import com.sujian.materaildesign.adapter.VideoRecyclerViewAdapter;
import com.sujian.materaildesign.delegate.PublicVideoFDelegate;
import com.sujian.materaildesign.delegate.TechnologyFDelegate;
import com.sujian.materaildesign.frame.presenter.FragmentPresenter;
import com.sujian.materaildesign.model.news.ITechnologyNewModel;
import com.sujian.materaildesign.model.news.NewEntity;
import com.sujian.materaildesign.model.news.TechnologyNewModel;
import com.sujian.materaildesign.model.video.BaiSiEntity;
import com.sujian.materaildesign.model.video.BaiSiModel;
import com.sujian.materaildesign.model.video.IBaiSiModel;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCFullScreenActivity;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import rx.Subscriber;

/**
 * 百思
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */
public class BaiSIFragment extends FragmentPresenter<PublicVideoFDelegate> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private IBaiSiModel baiSiModel = new BaiSiModel();

    private int page = 1;

    private int lastVisibleItem;

    private boolean isLoadMore;

    @Override
    protected Class<PublicVideoFDelegate> getDelegateClass() {
        return PublicVideoFDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getBaiSiFromService();
        initSwipeRefreshLayout();
        initRecylerView();
    }

    private void initRecylerView() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == viewDelegate.getAdapter().getItemCount()) {
                    swipeRefreshLayout.setRefreshing(true);
                    isLoadMore = true;
                    getBaiSiFromService();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = viewDelegate.getLinearLayoutManger().findLastVisibleItemPosition();
            }
        });


        viewDelegate.getAdapter().setOnItemClickListener(new VideoRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                JCFullScreenActivity.startActivity(getActivity(), viewDelegate.getList().get(position).getVideo_uri(), JCVideoPlayerStandard.class,
                        viewDelegate.getList().get(position).getText());
            }
        });
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                getBaiSiFromService();
            }
        });
    }

    private void getBaiSiFromService() {
        baiSiModel.getBaiSiModel(page, new Subscriber<BaiSiEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaiSiEntity baiSiEntity) {
                Logger.e(baiSiEntity.getShowapi_res_body().getPagebean().getContentlist().get(0).getText());
                viewDelegate.refreshRecyclerView(baiSiEntity.getShowapi_res_body().getPagebean().getContentlist(), isLoadMore);
            }
        });
        page++;
    }
}

package com.sujian.materaildesign.presenter;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.adapter.NewRecyclerViewAdapter;
import com.sujian.materaildesign.constant.Constant;
import com.sujian.materaildesign.delegate.TechnologyFDelegate;
import com.sujian.materaildesign.frame.presenter.FragmentPresenter;
import com.sujian.materaildesign.model.news.ITechnologyNewModel;
import com.sujian.materaildesign.model.news.NewEntity;
import com.sujian.materaildesign.model.news.TechnologyNewApi;
import com.sujian.materaildesign.model.news.TechnologyNewModel;
import com.sujian.materaildesign.uitls.RetrofitWapper;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 科技
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */
public class TechnologyFragment extends FragmentPresenter<TechnologyFDelegate> {

    @BindView(R.id.rv_technology)
    RecyclerView rv_technology;

    @BindView(R.id.srl_technology)
    SwipeRefreshLayout srl_technology;

    private ITechnologyNewModel technologyNewMOdel;

    private int page = 1;

    private int lastVisibleItem;

    private boolean isLoadMore;

    @Override
    protected Class<TechnologyFDelegate> getDelegateClass() {
        return TechnologyFDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getTechnologyNewFromService();
        initSwipeRefreshLayout();
        initRecylerView();
    }

    private void initRecylerView() {
        rv_technology.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == viewDelegate.getAdapter().getItemCount()) {
                    srl_technology.setRefreshing(true);
                    isLoadMore = true;
                    getTechnologyNewFromService();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = viewDelegate.getLinearLayoutManger().findLastVisibleItemPosition();
            }
        });


        viewDelegate.getAdapter().setOnItemClickListener(new NewRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", viewDelegate.getList().get(position).getUrl());
                startActivity(intent);
            }
        });
    }

    private void initSwipeRefreshLayout() {
        srl_technology.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                getTechnologyNewFromService();
            }
        });
    }

    private void getTechnologyNewFromService() {
        Logger.e("开始获取新闻数据");
        technologyNewMOdel = new TechnologyNewModel();
        technologyNewMOdel.getTechnologyNew(10, page, new Subscriber<NewEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.e("获取新闻数据失败");
                e.printStackTrace();
            }

            @Override
            public void onNext(NewEntity newEntity) {
                if (newEntity.getCode() == 200) {
                    Logger.e("科技新闻数据" + newEntity.toString());
                    viewDelegate.refreshRecyclerView(newEntity.getNewslist(), isLoadMore);
                }
            }
        });
        page++;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        technologyNewMOdel.unsubscribe();
    }
}

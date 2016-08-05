package com.sujian.materaildesign.presenter;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hwangjr.rxbus.RxBus;
import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.adapter.BaseRecylerViewAdapter;
import com.sujian.materaildesign.delegate.NetworkSongListDelegate;
import com.sujian.materaildesign.frame.presenter.ActivityPresenter;
import com.sujian.materaildesign.model.music.INetworkMusicModel;
import com.sujian.materaildesign.model.music.ISingleNetworkMusicModel;
import com.sujian.materaildesign.model.music.LinkSongList;
import com.sujian.materaildesign.model.music.NetworkMusicModel;
import com.sujian.materaildesign.model.music.SingleNetworkMusicModel;
import com.sujian.materaildesign.model.music.Song;
import com.sujian.materaildesign.model.music.SongInfo;
import com.sujian.materaildesign.player.PlayEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

public class NetworkSongListActivity extends ActivityPresenter<NetworkSongListDelegate> {

    private INetworkMusicModel networkMusicModel;
    private ISingleNetworkMusicModel singleModel = new SingleNetworkMusicModel();
    ;
    private int page;
    private int type;
    private int lastVisibleItem;
    private boolean isLoadMore;

    @BindView(R.id.tb_network)
    Toolbar tb_network;
    @BindView(R.id.fab_network)
    FloatingActionButton fab_network;
    @BindView(R.id.srl_network)
    SwipeRefreshLayout srl_network;
    @BindView(R.id.rv_network)
    RecyclerView rv_network;


    @Override
    protected Class<NetworkSongListDelegate> getDelegateClass() {
        return NetworkSongListDelegate.class;
    }


    @Override
    protected void initData() {
        super.initData();
        getType();
        fromService();
        clickItemEvent();
    }


    @Override
    protected void initView() {
        super.initView();
        SwipeRefreshLayoutEvent();
    }

    /**
     * 刷新事件
     */
    private void SwipeRefreshLayoutEvent() {

        rv_network.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = viewDelegate.getManager().findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == viewDelegate.getAdapter().getItemCount()) {
                    srl_network.setRefreshing(true);
                    isLoadMore = true;
                    fromService();
                }
            }
        });

        srl_network.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                fromService();
            }
        });
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tb_network.setNavigationIcon(R.mipmap.ic_arrow_back_white_36dp);
        tb_network.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @OnClick(R.id.fab_network)
    public void fab(View v) {
        Intent intent = new Intent(this, MusicActivity.class);
        startActivity(intent);
    }

    /**
     * 条目的点击事件处理
     */
    private void clickItemEvent() {
        viewDelegate.getAdapter().setOnItemClickListener(new BaseRecylerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                PlayEvent playEvent = new PlayEvent();
                playEvent.setSeekTo(position);
                playEvent.setAction(PlayEvent.Action.PLAY);
                playEvent.setQueue(viewDelegate.getList());
                RxBus.get().post(playEvent);
            }
        });
    }

    /**
     * 得到歌曲的类型
     */
    private void getType() {
        Intent intent = getIntent();
        type = intent.getExtras().getInt("type");
        Logger.e("得到类型" + type);
    }

    /**
     * 从服务器获取数据
     */
    private void fromService() {
        networkMusicModel = new NetworkMusicModel();
        networkMusicModel.getLinkSongList(type, 0, 0, new Subscriber<LinkSongList>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LinkSongList linkSongList) {
                getSupportActionBar().setTitle(linkSongList.getBillboard().getName());
                viewDelegate.setBG(linkSongList.getBillboard().getPic_s444());
            }
        });
        Logger.e("请求页" + page);

        singleModel.getSingleIfo(type, 10, page * 10, new Subscriber<List<Song>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Song> songs) {
                Logger.e("歌曲数据" + songs.toString());
                viewDelegate.refreshRecyclerView(songs, isLoadMore);
            }
        });
        page++;
    }


}

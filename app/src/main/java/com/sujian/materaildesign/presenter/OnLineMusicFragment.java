package com.sujian.materaildesign.presenter;

import android.content.Intent;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.adapter.BaseRecylerViewAdapter;
import com.sujian.materaildesign.constant.Constant;
import com.sujian.materaildesign.delegate.OnLineMusicDeletate;
import com.sujian.materaildesign.frame.presenter.FragmentPresenter;
import com.sujian.materaildesign.model.music.Billboard;
import com.sujian.materaildesign.model.music.BillboardModel;
import com.sujian.materaildesign.model.music.IBillboardModel;

import rx.Subscriber;

/**
 * 在线presenter
 * Created by sujian on 2016/7/17.
 * Mail:121116111@qq.com
 */
public class OnLineMusicFragment extends FragmentPresenter<OnLineMusicDeletate> {

    private IBillboardModel billboardModel;


    @Override
    protected Class<OnLineMusicDeletate> getDelegateClass() {
        return OnLineMusicDeletate.class;
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getBillboardData();
        itemClickEvevt();
    }

    /**
     * 条目点击事件
     */
    private void itemClickEvevt() {
        viewDelegate.getAdapter().setOnItemClickListener(new BaseRecylerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int type = Constant.MUSIC_TYPE[position];
                Intent intent = new Intent(getActivity(), NetworkSongListActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });
    }

    /**
     * 得到数据
     */
    private void getBillboardData() {
        Logger.e("开始获取榜单数据数据");
        billboardModel = new BillboardModel();
        billboardModel.getBillboard(Constant.MUSIC_TYPE, new Subscriber<Billboard>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.e("获取榜单数据失败");
                viewDelegate.snackbar("获取榜单信息失败！请检查网络");
                e.printStackTrace();
            }

            @Override
            public void onNext(Billboard billboard) {
                Logger.e("榜单数据" + billboard.toString());
                viewDelegate.refreshRecyclerView(billboard);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        billboardModel.unsubscribe();
    }
}

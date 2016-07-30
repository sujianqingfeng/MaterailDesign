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

    private IBillboardModel billboardModel = new BillboardModel();


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
        billboardModel.getBillboard(Constant.MUSIC_TYPE, new Subscriber<Billboard>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Billboard billboard) {
                Logger.e("榜单数据" + billboard.toString());
                viewDelegate.refreshRecyclerView(billboard);
            }
        });
    }



}

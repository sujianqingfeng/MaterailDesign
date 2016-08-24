package com.sujian.materaildesign.presenter;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.adapter.PictureAdapter;
import com.sujian.materaildesign.delegate.BaiDuPictureFDelegate;
import com.sujian.materaildesign.frame.presenter.FragmentPresenter;
import com.sujian.materaildesign.model.picture.IPictureModel;
import com.sujian.materaildesign.model.picture.PictureEntity;
import com.sujian.materaildesign.model.picture.PictureModel;

import butterknife.BindView;
import rx.Subscriber;

/**
 * 百度图片fragemnt
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */
public class BaiDuPictureFragment extends FragmentPresenter<BaiDuPictureFDelegate> {
    @BindView(R.id.rv_picture)
    XRecyclerView rv_picture;




    boolean isLoadMore;

    private IPictureModel pictureModel = new PictureModel();
    @Override
    protected Class<BaiDuPictureFDelegate> getDelegateClass() {
        return BaiDuPictureFDelegate.class;
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getDataFromService();
        initView();
    }


    protected void initView() {

        rv_picture.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                getDataFromService();
            }

            @Override
            public void onLoadMore() {
                isLoadMore = true;
                getDataFromService();
            }
        });

        viewDelegate.getPictureAdapter().setOnItemClickListener(new PictureAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                viewDelegate.snackbar(viewDelegate.getList().get(data).getUrl());
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", viewDelegate.getList().get(data).getUrl());
                startActivity(intent);
            }
        });

    }

    public void getDataFromService() {

        pictureModel.getPicture(10, new Subscriber<PictureEntity>() {


            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(PictureEntity pictureEntity) {
                if (pictureEntity.getCode() == 200) {
                    viewDelegate.refreshView(pictureEntity.getNewslist(),isLoadMore);
                }
            }
        });

    }
}

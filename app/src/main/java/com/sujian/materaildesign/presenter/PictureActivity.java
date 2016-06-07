package com.sujian.materaildesign.presenter;


import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.adapter.PictureAdapter;
import com.sujian.materaildesign.delegate.PictureDelegate;
import com.sujian.materaildesign.frame.presenter.ActivityPresenter;
import com.sujian.materaildesign.model.picture.IPictureModel;
import com.sujian.materaildesign.model.picture.PictureEntity;
import com.sujian.materaildesign.model.picture.PictureModel;

import butterknife.BindView;
import rx.Subscriber;

public class PictureActivity extends ActivityPresenter<PictureDelegate> {

    @BindView(R.id.rv_picture)
    XRecyclerView rv_picture;

    @BindView(R.id.tb_picture)
    Toolbar tb_picture;

    private IPictureModel pictureModel = new PictureModel();

    @Override
    protected Class<PictureDelegate> getDelegateClass() {
        return PictureDelegate.class;
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getDataFromService();
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        tb_picture.setNavigationIcon(R.mipmap.ic_arrow_back_white_36dp);
        tb_picture.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        viewDelegate.getPictureAdapter().setOnItemClickListener(new PictureAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                viewDelegate.snackbar(viewDelegate.getList().get(data).getUrl());
                Intent intent = new Intent(PictureActivity.this, WebActivity.class);
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
                    viewDelegate.refreshView(pictureEntity.getNewslist());
                }
            }
        });

    }


}

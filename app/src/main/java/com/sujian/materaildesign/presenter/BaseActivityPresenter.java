package com.sujian.materaildesign.presenter;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;


import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;


import com.sujian.materaildesign.R;
import com.sujian.materaildesign.frame.presenter.ActivityPresenter;
import com.sujian.materaildesign.frame.view.AppDelegate;
import com.sujian.materaildesign.uitls.MyActivityManager;

import butterknife.BindView;

/**
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */
public abstract class BaseActivityPresenter<TT extends AppDelegate> extends ActivityPresenter<TT> {

    @BindView(R.id.dl_main)
    DrawerLayout dl_main;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.initWindow();
    }

    @Override
    public void onBackPressed() {
        if (dl_main.isDrawerOpen(GravityCompat.START)) {
            dl_main.closeDrawer(GravityCompat.START);
        } else {
            viewDelegate.snackbar(fab, "你确定退出程序么？骚年", "确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyActivityManager.AppExit(BaseActivityPresenter.this);
                }
            });

        }
    }

}

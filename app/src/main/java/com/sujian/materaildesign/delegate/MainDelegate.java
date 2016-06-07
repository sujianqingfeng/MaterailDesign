package com.sujian.materaildesign.delegate;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;


import com.sujian.materaildesign.R;
import com.sujian.materaildesign.frame.view.AppDelegate;
import com.sujian.materaildesign.presenter.MainActivity;

import butterknife.BindView;

/**
 * 主页视图
 * Created by sujian on 2016/5/29.
 * Mail:121116111@qq.com
 */

public class MainDelegate extends AppDelegate {
    @BindView(R.id.main_toorbar)
    Toolbar main_toorbar;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }


    @Override
    public Toolbar getToolbar() {
        main_toorbar.setTitle("个人介绍   素笺");


        return get(R.id.main_toorbar);
    }


}

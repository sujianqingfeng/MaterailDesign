/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sujian.materaildesign.frame.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * View delegate base class
 * 视图层代理的基类
 *
 * @author kymjs (http://www.kymjs.com/) on 10/23/15.
 */
public abstract class AppDelegate implements IDelegate {
    protected final SparseArray<View> mViews = new SparseArray<View>();

    protected View rootView;

    public abstract int getRootLayoutId();

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int rootLayoutId = getRootLayoutId();
        rootView = inflater.inflate(rootLayoutId, container, false);
        ButterKnife.bind(this, rootView);
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    @Override
    public void initWidget() {
    }

    public <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    public <T extends View> T get(int id) {
        return (T) bindView(id);
    }

    public void setOnClickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            get(id).setOnClickListener(listener);
        }
    }

    public void snackbar(CharSequence msg) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_SHORT).show();
    }


    public void snackbar(View view, CharSequence msg, String actionMsg, View.OnClickListener listener) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
                .setAction(actionMsg, listener)
                .setDuration(3000)
                .show();
    }

    public <T extends AppCompatActivity> T getActivity() {
        return (T) rootView.getContext();
    }


    /**
     * 半透明状态栏
     */
    public void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#50000000"));
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }
}

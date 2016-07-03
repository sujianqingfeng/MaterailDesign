package com.sujian.materaildesign.delegate;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.adapter.BaseFragmentPagerAdapter;
import com.sujian.materaildesign.frame.presenter.FragmentPresenter;
import com.sujian.materaildesign.frame.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 含有viewpager页面的view代理
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */
public abstract class BaseViewPagerDelegate extends MenuDelegate {

    //viewpager
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    //tablayout
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    //装载fragment
    List<FragmentPresenter> fragments;
    //装载标题
    List<String> titles;
    //viewpager的适配器
    BaseFragmentPagerAdapter baseFragmentPagerAdapter;

    @Override
    public void initWidget() {
        super.initWidget();
        initViewPager();
    }

    /**
     * 初始化viewpager
     */
    protected void initViewPager() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();

        baseFragmentPagerAdapter = new BaseFragmentPagerAdapter<>(
                getActivity().getSupportFragmentManager(),
                fragments,
                titles);
        viewpager.setAdapter(baseFragmentPagerAdapter);

        initTabLayout();
    }

    /**
     * 初始化tablayout
     */
    protected void initTabLayout() {
        tabLayout.setupWithViewPager(viewpager);
    }


    @Override
    public abstract int getRootLayoutId();
}

package com.sujian.materaildesign.delegate;

import android.support.v7.widget.Toolbar;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.presenter.BaiDuPictureFragment;
import com.sujian.materaildesign.presenter.GankPictureFragment;
import com.sujian.materaildesign.presenter.InternationalFragment;
import com.sujian.materaildesign.presenter.SportsFragment;
import com.sujian.materaildesign.presenter.TechnologyFragment;
import com.sujian.materaildesign.uitls.ActivityFragmentInject;

/**
 * Created by sujian on 2016/6/11.
 * Mail:121116111@qq.com
 */
@ActivityFragmentInject(menuDefaultCheckedItem = R.id.navigation_sub_item_2)
public class PictureDelegate extends BaseViewPagerDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public Toolbar getToolbar() {
        main_toorbar.setTitle(R.string.pic);
        return get(R.id.main_toorbar);
    }

    @Override
    protected void initViewPager() {
        super.initViewPager();

        BaiDuPictureFragment baiDuPictureFragment=new BaiDuPictureFragment();
        GankPictureFragment gankPictureFragment=new GankPictureFragment();
        fragments.add(baiDuPictureFragment);
        fragments.add(gankPictureFragment);

        titles.add("百度图片");
        titles.add("Gank图片");


        baseFragmentPagerAdapter.notifyDataSetChanged();

    }
}

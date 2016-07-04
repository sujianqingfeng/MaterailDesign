package com.sujian.materaildesign.delegate;

import android.support.v7.widget.Toolbar;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.presenter.BaiSIFragment;
import com.sujian.materaildesign.presenter.InternationalFragment;
import com.sujian.materaildesign.presenter.SportsFragment;
import com.sujian.materaildesign.presenter.TechnologyFragment;
import com.sujian.materaildesign.uitls.ActivityFragmentInject;

/**
 * Created by sujian on 2016/6/11.
 * Mail:121116111@qq.com
 */
@ActivityFragmentInject(menuDefaultCheckedItem = R.id.navigation_sub_item_4)
public class VideoDelegate extends BaseViewPagerDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public Toolbar getToolbar() {
        main_toorbar.setTitle(R.string.video);
        return get(R.id.main_toorbar);
    }

    @Override
    protected void initViewPager() {
        super.initViewPager();
        BaiSIFragment baiSIFragment = new BaiSIFragment();

        fragments.add(baiSIFragment);


        titles.add("百思");


        baseFragmentPagerAdapter.notifyDataSetChanged();

    }
}

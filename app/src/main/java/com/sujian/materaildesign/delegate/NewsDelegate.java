package com.sujian.materaildesign.delegate;

import android.support.v7.widget.Toolbar;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.frame.view.AppDelegate;
import com.sujian.materaildesign.presenter.InternationalFragment;
import com.sujian.materaildesign.presenter.SportsFragment;
import com.sujian.materaildesign.presenter.TechnologyFragment;
import com.sujian.materaildesign.uitls.ActivityFragmentInject;

/**
 * Created by sujian on 2016/6/11.
 * Mail:121116111@qq.com
 */
@ActivityFragmentInject(menuDefaultCheckedItem = R.id.navigation_sub_item_5)
public class NewsDelegate extends BaseViewPagerDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public Toolbar getToolbar() {
        main_toorbar.setTitle(R.string.news);
        return get(R.id.main_toorbar);
    }

    @Override
    protected void initViewPager() {
        super.initViewPager();
        TechnologyFragment technologyFragment = new TechnologyFragment();
        InternationalFragment internationalFragment = new InternationalFragment();
        SportsFragment sportsFragment = new SportsFragment();

        fragments.add(technologyFragment);
        fragments.add(internationalFragment);
        fragments.add(sportsFragment);

        titles.add(getActivity().getString(R.string.technology));
        titles.add(getActivity().getString(R.string.international));
        titles.add(getActivity().getString(R.string.sports));

        baseFragmentPagerAdapter.notifyDataSetChanged();

    }
}

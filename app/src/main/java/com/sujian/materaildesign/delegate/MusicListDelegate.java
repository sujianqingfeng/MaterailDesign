package com.sujian.materaildesign.delegate;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.presenter.BaiSIFragment;
import com.sujian.materaildesign.presenter.LocalMusicFragment;
import com.sujian.materaildesign.presenter.OnLineMusicFragment;
import com.sujian.materaildesign.uitls.ActivityFragmentInject;

import butterknife.BindView;

/**
 * 音乐列表view
 * Created by sujian on 2016/6/11.
 * Mail:121116111@qq.com
 */
@ActivityFragmentInject(menuDefaultCheckedItem = R.id.navigation_sub_item_3)
public class MusicListDelegate extends BaseViewPagerDelegate {
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public Toolbar getToolbar() {
        main_toorbar.setTitle(R.string.music);
        return get(R.id.main_toorbar);
    }

    @Override
    protected void initViewPager() {
        super.initViewPager();
        LocalMusicFragment localMusicFragment = new LocalMusicFragment();
        OnLineMusicFragment onLineMusicFragment = new OnLineMusicFragment();

        fragments.add(localMusicFragment);
        fragments.add(onLineMusicFragment);

        titles.add("本地音乐");
        titles.add("在线音乐");


        baseFragmentPagerAdapter.notifyDataSetChanged();

    }
}

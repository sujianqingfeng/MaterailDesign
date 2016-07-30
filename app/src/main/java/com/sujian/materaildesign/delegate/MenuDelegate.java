package com.sujian.materaildesign.delegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.constant.Constant;
import com.sujian.materaildesign.frame.view.AppDelegate;
import com.sujian.materaildesign.presenter.MainActivity;
import com.sujian.materaildesign.presenter.MusicActivity;
import com.sujian.materaildesign.presenter.MusicListActivity;
import com.sujian.materaildesign.presenter.NewsActivity;
import com.sujian.materaildesign.presenter.PictureActivity;
import com.sujian.materaildesign.presenter.VideoActivity;
import com.sujian.materaildesign.uitls.ActivityFragmentInject;

import butterknife.BindView;
import rx.functions.Action1;


/**
 * 菜单view
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */

public abstract class MenuDelegate extends AppDelegate {

    @BindView(R.id.dl_main)
    DrawerLayout dl_main;
    @BindView(R.id.nv_menu)
    NavigationView nv_menu;
    @BindView(R.id.main_toorbar)
    Toolbar main_toorbar;

    protected int mMenuDefaultCheckedItem;
    protected int mToolbarTitle;


    @Override
    public abstract int getRootLayoutId();


    @Override
    public void initWidget() {
        super.initWidget();
        initAnnotation();
        initMenu();
        initToolBar();

    }

    /**
     * 初始化注解
     */
    private void initAnnotation() {
        if (getClass().isAnnotationPresent(ActivityFragmentInject.class)) {
            ActivityFragmentInject annotation = getClass()
                    .getAnnotation(ActivityFragmentInject.class);
            mToolbarTitle = annotation.toolbarTitle();
            mMenuDefaultCheckedItem = annotation.menuDefaultCheckedItem();
        } else {
            throw new RuntimeException(
                    "Class must add annotations of ActivityFragmentInitParams.class");
        }
    }

    /**
     * 初始化toolbar
     */
    private void initToolBar() {

        ActionBar actionBar = getActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(getActivity(), dl_main, main_toorbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        dl_main.setDrawerListener(mDrawerToggle);
    }


    /**
     * 初始化侧边菜单
     */
    private void initMenu() {
        if (mMenuDefaultCheckedItem != -1 && mMenuDefaultCheckedItem != 0) {

            nv_menu.setCheckedItem(mMenuDefaultCheckedItem);
        }

        nv_menu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Class mClass = null;
                switch (item.getItemId()) {
                    case R.id.navigation_sub_item_1:
                        mClass = MainActivity.class;
                        break;
                    case R.id.navigation_sub_item_2:
                        mClass = PictureActivity.class;
                        break;
                    case R.id.navigation_sub_item_3:
                        mClass = MusicListActivity.class;
                        break;

                    case R.id.navigation_sub_item_4:
                        mClass = VideoActivity.class;
                        break;

                    case R.id.navigation_sub_item_5:
                        mClass = NewsActivity.class;
                        break;

                    case R.id.menu_night:
                        initTheme();
                        break;
                }
                dl_main.closeDrawers();
                meunStartActivity(mClass);
                return false;
            }
        });
    }


    /**
     * 主题
     */
    private void initTheme() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());
        RxSharedPreferences rxPreferences = RxSharedPreferences.create(preferences);
        Preference<Boolean> isNight = rxPreferences.getBoolean(Constant.ISNIGHT, false);
        isNight.asObservable()
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            getActivity().getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            // 调用 recreate() 使设置生效
                            getActivity().recreate();
                        } else {
                            getActivity().getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            // 调用 recreate() 使设置生效
                            getActivity().recreate();
                        }
                    }
                });
        isNight.set(!isNight.get());
    }

    //页面跳转
    public void meunStartActivity(Class<?> cls) {
        if (cls != null) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), cls);
            // 此标志用于启动一个Activity的时候，若栈中存在此Activity实例，则把它调到栈顶。不创建多一个
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            getActivity().finish();
        }
    }


}

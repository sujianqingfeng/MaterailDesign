package com.sujian.materaildesign.presenter;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.constant.Constant;
import com.sujian.materaildesign.delegate.MainDelegate;
import com.sujian.materaildesign.frame.presenter.ActivityPresenter;
import com.sujian.materaildesign.uitls.RetrofitWapper;
import com.sujian.materaildesign.uitls.SharedPreferencesUitls;
import com.sujian.materaildesign.uitls.T;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

import static com.sujian.materaildesign.R.id.cancel_action;
import static com.sujian.materaildesign.R.id.main_toorbar;
import static com.sujian.materaildesign.R.id.navigation_sub_item_1;
import static com.sujian.materaildesign.R.id.navigation_sub_item_2;


/**
 * 主页的presenter
 */
public class MainActivity extends ActivityPresenter<MainDelegate> {

    @BindView(R.id.dl_main)
    DrawerLayout dl_main;
    @BindView(R.id.nv_menu)
    NavigationView nv_menu;
    @BindView(R.id.main_toorbar)
    Toolbar main_toorbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.initWindow();
    }


    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }


    @Override
    protected void initView() {
        super.initView();
        initMenu();
    }



    @Override
    protected void initToolbar() {
        super.initToolbar();
        main_toorbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_36dp);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, dl_main, main_toorbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        dl_main.setDrawerListener(mDrawerToggle);

    }

    /**
     * 主题
     */
    private void initTheme() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        RxSharedPreferences rxPreferences = RxSharedPreferences.create(preferences);
        Preference<Boolean> isNight = rxPreferences.getBoolean(Constant.ISNIGHT, false);
        isNight.asObservable()
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            // 调用 recreate() 使设置生效
                            recreate();
                        } else {
                            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            // 调用 recreate() 使设置生效
                            recreate();
                        }
                    }
                });
        isNight.set(!isNight.get());
    }

    /**
     * 初始化侧边菜单
     */
    private void initMenu() {

        nv_menu.setCheckedItem(R.id.navigation_sub_item_1);
        nv_menu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                dl_main.closeDrawers();
                switch (item.getItemId()) {
                    case navigation_sub_item_1:

                        break;
                    case navigation_sub_item_2:
                        startActivity(new Intent(MainActivity.this, PictureActivity.class));
                        break;
                    case R.id.navigation_sub_item_3:
                        startActivity(new Intent(MainActivity.this, MusicActivity.class));
                        break;

                    case R.id.menu_night:
                        initTheme();
                        break;
                }
                nv_menu.setCheckedItem(item.getItemId());
                return false;
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (dl_main.isDrawerOpen(GravityCompat.START)) {
            dl_main.closeDrawer(GravityCompat.START);
        } else {
            viewDelegate.snackbar("你确定退出程序么？骚年", "确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    System.exit(0);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            });
        }
    }


}

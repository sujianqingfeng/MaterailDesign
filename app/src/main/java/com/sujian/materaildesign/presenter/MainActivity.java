package com.sujian.materaildesign.presenter;


import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.delegate.MainDelegate;
import com.sujian.materaildesign.frame.presenter.ActivityPresenter;

import butterknife.BindView;

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
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }


    @Override
    protected void initView() {
        super.initView();
        initMenu();
        initActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                dl_main.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initActionBar() {
        main_toorbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_36dp);
//        final ActionBar actionBar =getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowHomeEnabled(true);
//        }
    }


    private void initMenu() {
        nv_menu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case navigation_sub_item_1:

                        break;
                    case navigation_sub_item_2:
                        dl_main.closeDrawers();
                        startActivity(new Intent(MainActivity.this, PictureActivity.class));
                        break;
                    case R.id.navigation_sub_item_3:
                        startActivity(new Intent(MainActivity.this, MusicActivity.class));
                        break;
                }
                return false;
            }
        });
    }


}

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
public class MainActivity extends BaseActivityPresenter<MainDelegate> {

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




}

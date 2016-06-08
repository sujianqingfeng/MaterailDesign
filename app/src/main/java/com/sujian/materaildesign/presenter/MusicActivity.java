package com.sujian.materaildesign.presenter;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.delegate.MusicDalegale;
import com.sujian.materaildesign.frame.presenter.ActivityPresenter;

import butterknife.BindView;

/**
 * 音乐presenter
 */
public class MusicActivity extends ActivityPresenter<MusicDalegale> {

    @BindView(R.id.tb_music)
    Toolbar tb_music;

    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.initWindow();
    }

    @Override
    protected Class<MusicDalegale> getDelegateClass() {
        return MusicDalegale.class;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tb_music.setNavigationIcon(R.mipmap.ic_arrow_back_white_36dp);
        tb_music.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.e(searchView.getMeasuredWidth() + "----------------------");
                if (searchView.getMeasuredWidth() > 600) {
                    searchView.onActionViewCollapsed();
                } else {
                    finish();
                }

            }
        });

        tb_music.inflateMenu(R.menu.music_menu);
        tb_music.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_more:
                        viewDelegate.snackbar("更多被点击");
                        break;
                }
                return false;
            }
        });
        searchView = (SearchView) tb_music.findViewById(R.id.toolbar_serach);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewDelegate.snackbar("提交的数据" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewDelegate.snackbar("新的文本" + newText);
                return false;
            }
        });




    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {//MENU键

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

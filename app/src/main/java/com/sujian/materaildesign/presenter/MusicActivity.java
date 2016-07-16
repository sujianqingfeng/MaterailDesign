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
import co.mobiwise.library.MusicPlayerView;

/**
 * 音乐presenter
 */
public class MusicActivity extends ActivityPresenter<MusicDalegale> {

    @BindView(R.id.tb_music)
    Toolbar tb_music;

    @BindView(R.id.mpv)
    MusicPlayerView mpv;

    private SearchView searchView;
    //宽度
    private int searchWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.initWindow();
    }

    @Override
    protected void initData() {
        super.initData();
        mpv.setCoverURL("http://i.gtimg.cn/music/photo/mid_album_300/2/5/001xdVLB17WQ25.jpg");
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
                if (searchView.getMeasuredWidth() > searchWidth) {
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
        searchWidth = searchView.getMeasuredWidth();
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

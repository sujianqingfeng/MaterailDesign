package com.sujian.materaildesign.presenter;

import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.delegate.MusicDalegale;
import com.sujian.materaildesign.frame.presenter.ActivityPresenter;

import butterknife.BindView;

public class MusicActivity extends ActivityPresenter<MusicDalegale> {

    @BindView(R.id.tb_music)
    Toolbar tb_music;

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
                finish();
            }
        });

        tb_music.inflateMenu(R.menu.music_menu);

        tb_music.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_serach:
                        viewDelegate.snackbar("搜索被点击");
                        break;
                }
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

package com.sujian.materaildesign.presenter;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.delegate.MusicListDelegate;
import com.sujian.materaildesign.player.PlayerService;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 音乐列表
 */
public class MusicListActivity extends BaseActivityPresenter<MusicListDelegate> {

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected Class<MusicListDelegate> getDelegateClass() {
        return MusicListDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        startService(new Intent(MusicListActivity.this, PlayerService.class));
    }

    @OnClick({R.id.fab})
    public void fab(View view) {
        Intent intent = new Intent(this, MusicActivity.class);
        startActivity(intent);
    }
}

package com.sujian.materaildesign.presenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sujian.materaildesign.delegate.MusicListDelegate;

/**
 * 音乐列表
 */
public class MusicListActivity extends BaseActivityPresenter<MusicListDelegate> {


    @Override
    protected Class<MusicListDelegate> getDelegateClass() {
        return MusicListDelegate.class;
    }
}

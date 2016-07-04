package com.sujian.materaildesign.presenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sujian.materaildesign.delegate.VideoPlayerDelegate;
import com.sujian.materaildesign.frame.presenter.ActivityPresenter;

public class VideoPlayerActivity extends ActivityPresenter<VideoPlayerDelegate> {

    @Override
    protected Class getDelegateClass() {
        return VideoPlayerDelegate.class;
    }
}

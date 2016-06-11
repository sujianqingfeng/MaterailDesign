package com.sujian.materaildesign.presenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.delegate.NewsDelegate;
import com.sujian.materaildesign.frame.presenter.ActivityPresenter;

/**
 * 新闻presenter
 */
public class NewsActivity extends ActivityPresenter<NewsDelegate> {

    @Override
    protected Class<NewsDelegate> getDelegateClass() {
        return NewsDelegate.class;
    }
}

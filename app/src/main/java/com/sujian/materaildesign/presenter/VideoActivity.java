package com.sujian.materaildesign.presenter;

import com.sujian.materaildesign.delegate.NewsDelegate;
import com.sujian.materaildesign.delegate.VideoDelegate;

/**
 * 新闻presenter
 */
public class VideoActivity extends BaseActivityPresenter<VideoDelegate> {

    @Override
    protected Class<VideoDelegate> getDelegateClass() {
        return VideoDelegate.class;
    }

}

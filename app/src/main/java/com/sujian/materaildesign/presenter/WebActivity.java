package com.sujian.materaildesign.presenter;

import com.sujian.materaildesign.delegate.WedDelegate;
import com.sujian.materaildesign.frame.presenter.ActivityPresenter;

public class WebActivity extends ActivityPresenter<WedDelegate> {

    @Override
    protected Class<WedDelegate> getDelegateClass() {
        return WedDelegate.class;
    }


}

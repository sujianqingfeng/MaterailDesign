package com.sujian.materaildesign.presenter;

import com.sujian.materaildesign.delegate.GankPictureFDelegate;
import com.sujian.materaildesign.frame.presenter.FragmentPresenter;

/**
 * Created by sujian on 2016/8/24.
 * Mail:121116111@qq.com
 */
public class GankPictureFragment extends FragmentPresenter<GankPictureFDelegate> {
    @Override
    protected Class<GankPictureFDelegate> getDelegateClass() {
        return GankPictureFDelegate.class;
    }
}

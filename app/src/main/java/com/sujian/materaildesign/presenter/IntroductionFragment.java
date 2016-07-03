package com.sujian.materaildesign.presenter;

import com.sujian.materaildesign.delegate.IntroductionDelegate;
import com.sujian.materaildesign.frame.presenter.FragmentPresenter;

/**
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */
public class IntroductionFragment extends FragmentPresenter<IntroductionDelegate> {
    @Override
    protected Class<IntroductionDelegate> getDelegateClass() {
        return IntroductionDelegate.class;
    }

}

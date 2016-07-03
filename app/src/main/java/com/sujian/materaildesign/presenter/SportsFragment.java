package com.sujian.materaildesign.presenter;

import com.sujian.materaildesign.delegate.SportsFDelegate;
import com.sujian.materaildesign.delegate.TechnologyFDelegate;
import com.sujian.materaildesign.frame.presenter.FragmentPresenter;

/**
 * 体育
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */
public class SportsFragment extends FragmentPresenter<SportsFDelegate> {
    @Override
    protected Class<SportsFDelegate> getDelegateClass() {
        return SportsFDelegate.class;
    }
}

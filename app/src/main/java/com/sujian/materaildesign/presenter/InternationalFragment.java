package com.sujian.materaildesign.presenter;

import com.sujian.materaildesign.delegate.InternationalFDelegate;
import com.sujian.materaildesign.delegate.TechnologyFDelegate;
import com.sujian.materaildesign.frame.presenter.FragmentPresenter;

/**
 * 国际
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */
public class InternationalFragment extends FragmentPresenter<InternationalFDelegate> {
    @Override
    protected Class<InternationalFDelegate> getDelegateClass() {
        return InternationalFDelegate.class;
    }
}

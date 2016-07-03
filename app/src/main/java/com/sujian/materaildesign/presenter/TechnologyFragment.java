package com.sujian.materaildesign.presenter;

import com.sujian.materaildesign.delegate.TechnologyFDelegate;
import com.sujian.materaildesign.frame.presenter.FragmentPresenter;

/**
 * 科技
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */
public class TechnologyFragment extends FragmentPresenter<TechnologyFDelegate> {
    @Override
    protected Class<TechnologyFDelegate> getDelegateClass() {
        return TechnologyFDelegate.class;
    }
}

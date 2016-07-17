package com.sujian.materaildesign.presenter;

import com.sujian.materaildesign.delegate.OnLineMusicDeletate;
import com.sujian.materaildesign.frame.presenter.FragmentPresenter;

/**
 * 在线presenter
 * Created by sujian on 2016/7/17.
 * Mail:121116111@qq.com
 */
public class OnLineMusicFragment extends FragmentPresenter<OnLineMusicDeletate> {
    @Override
    protected Class<OnLineMusicDeletate> getDelegateClass() {
        return OnLineMusicDeletate.class;
    }
}

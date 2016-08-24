package com.sujian.materaildesign.presenter;


import android.support.v7.widget.Toolbar;

import com.sujian.materaildesign.R;
import com.sujian.materaildesign.delegate.BaseViewPagerDelegate;
import com.sujian.materaildesign.delegate.PictureDelegate;
import com.sujian.materaildesign.uitls.ActivityFragmentInject;
@ActivityFragmentInject(menuDefaultCheckedItem = R.id.navigation_sub_item_2)
public class PictureActivity extends BaseActivityPresenter<PictureDelegate> {


    @Override
    protected Class<PictureDelegate> getDelegateClass() {
        return PictureDelegate.class;
    }
}

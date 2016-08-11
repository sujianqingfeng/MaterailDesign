package com.sujian.materaildesign.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerView 间距分割线
 * Created by sujian on 2016/8/4.
 * Mail:121116111@qq.com
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int itemCount = parent.getAdapter().getItemCount();
        int pos = parent.getChildAdapterPosition(view);

        outRect.left = 0;
        outRect.right = 0;
        outRect.top = 0;

        outRect.bottom = space;
    }
}

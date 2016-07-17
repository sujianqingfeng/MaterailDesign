package com.sujian.materaildesign.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by sujian on 2016/7/17.
 * Mail:121116111@qq.com
 */
public class RecyclerViewBaseHoler extends RecyclerView.ViewHolder implements View.OnClickListener {

    private BaseRecylerViewAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public RecyclerViewBaseHoler(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    /**
     * 设置单击事件监听
     *
     * @param listener
     */
    public void setOnItemClickListener(BaseRecylerViewAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}

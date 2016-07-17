package com.sujian.materaildesign.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.R;

import java.util.List;

/**
 * 所有recyclerview的基类
 * Created by sujian on 2016/7/17.
 * Mail:121116111@qq.com
 */
public abstract class BaseRecylerViewAdapter<T, H extends RecyclerViewBaseHoler> extends RecyclerView.Adapter {

    private List<T> list;
    public T data;
    public H viewHolder;
    private BaseRecylerViewAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public BaseRecylerViewAdapter(List<T> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), setItemLayout(), null);
        return setViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder = (H) holder;
        viewHolder.itemView.setTag(position);
        data = list.get(position);
        viewHolder.setOnItemClickListener(mOnItemClickListener);
        refreshView();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 设置单击事件监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    /**
     * 设置item布局id
     *
     * @return
     */
    protected abstract int setItemLayout();

    /**
     * 设置holder
     *
     * @return
     */
    protected abstract RecyclerViewBaseHoler setViewHolder(View view);

    /**
     * 设置数据以及事件处理
     */
    protected abstract void refreshView();


    /**
     * 点击接口
     */
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

}


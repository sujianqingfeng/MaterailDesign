package com.sujian.materaildesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.model.news.NewEntity;
import com.sujian.materaildesign.model.video.BaiSiEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 视频的recyclerview的适配器
 * Created by sujian on 2016/7/3.
 * Mail:121116111@qq.com
 */
public class VideoRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<BaiSiEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public VideoRecyclerViewAdapter(Context context, List<BaiSiEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data) {
        this.context = context;
        this.data = data;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.video_recycle_item_layout, null);
        NewViewHolder viewHolder = new NewViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewViewHolder newViewHolder = (NewViewHolder) holder;
        newViewHolder.itemView.setTag(position);
        BaiSiEntity.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean = data.get(position);
        newViewHolder.textView.setText(contentlistBean.getText());
        Glide.with(context)
                .load(contentlistBean.getProfile_image())
                .error(R.mipmap.ic_launcher)
                .into(newViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class NewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_video)
        ImageView imageView;
        @BindView(R.id.tv_video)
        TextView textView;

        public NewViewHolder(View itemView) {
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
    }


    /**
     * 点击接口
     */
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }


}

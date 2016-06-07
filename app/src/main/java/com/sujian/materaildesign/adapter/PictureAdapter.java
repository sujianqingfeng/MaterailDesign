package com.sujian.materaildesign.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.orhanobut.logger.Logger;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.model.picture.PictureEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 图片的适配器
 * Created by sujian on 2016/5/30.
 * Mail:121116111@qq.com
 */
public class PictureAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<PictureEntity.PictureListItem> data;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public PictureAdapter(Context context, List<PictureEntity.PictureListItem> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_recycle_item_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder myHolder = (MyViewHolder) holder;
        myHolder.itemView.setTag(position);

        PictureEntity.PictureListItem pictureListItem = data.get(position);
        Logger.e("item数据" + position + "--" + pictureListItem.toString());
        myHolder.title.setText(pictureListItem.getTitle());
        Glide.with(context)
                .load(pictureListItem.getPicUrl())
                .asBitmap()
                .error(R.mipmap.ic_launcher)
                .into(new BitmapImageViewTarget(myHolder.picture) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        super.onResourceReady(resource, glideAnimation);
                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            public void onGenerated(Palette p) {
                                int vibrant = p.getLightVibrantColor(0x000000);
                                myHolder.title.setBackgroundColor(vibrant);
                            }
                        });
                    }
                });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_picture_recycle_item_title)
        TextView title;
        @BindView(R.id.iv_picture_recycle_item_img)
        ImageView picture;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
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

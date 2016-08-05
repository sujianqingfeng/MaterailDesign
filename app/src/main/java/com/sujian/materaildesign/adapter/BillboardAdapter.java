package com.sujian.materaildesign.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lid.lib.LabelImageView;
import com.sujian.materaildesign.R;
import com.sujian.materaildesign.model.music.Billboard;

import java.util.List;

import butterknife.BindView;

/**
 * Created by sujian on 2016/7/30.
 * Mail:121116111@qq.com
 */
public class BillboardAdapter extends BaseRecylerViewAdapter<Billboard, BillboardViewHolder> {
    private Context context;

    public BillboardAdapter(List<Billboard> list, Context context) {
        super(list);
        this.context = context;
    }

    @Override
    protected int setItemLayout() {
        return R.layout.billboard_item_lauout;
    }

    @Override
    protected RecyclerViewBaseHoler setViewHolder(View view) {
        return new BillboardViewHolder(view);
    }

    @Override
    protected void refreshView() {
        Glide.with(context)
                .load(data.getBillboardBean().getPic_s640())
                .error(R.mipmap.ic_launcher)
                .into(viewHolder.liv_img);
        viewHolder.liv_img.setLabelText(data.getBillboardBean().getName());
        if (data.getSongListBeen().size() == 3) {
            viewHolder.tv_one.setText("1." + data.getSongListBeen().get(0).getTitle());
            viewHolder.tv_two.setText("2." + data.getSongListBeen().get(1).getTitle());
            viewHolder.tv_three.setText("3." + data.getSongListBeen().get(2).getTitle());
        }
    }
}


class BillboardViewHolder extends RecyclerViewBaseHoler {

    @BindView(R.id.liv_img)
    LabelImageView liv_img;
    @BindView(R.id.tv_one)
    TextView tv_one;
    @BindView(R.id.tv_two)
    TextView tv_two;
    @BindView(R.id.tv_three)
    TextView tv_three;


    public BillboardViewHolder(View itemView) {
        super(itemView);
    }
}
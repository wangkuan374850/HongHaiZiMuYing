package com.example.wangkuan.honghaizimuying.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangkuan.honghaizimuying.R;
import com.example.wangkuan.honghaizimuying.bean.FeiLieBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by wangkuan on 2016/11/22.
 */
public class FeiLeiNeiRongAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private FeiLieBean feiLieBean;
    private Context context;
    private int ye;
    private DisplayImageOptions options;

    public FeiLeiNeiRongAdapter(FeiLieBean feiLieBean, Context context, int ye, DisplayImageOptions options) {
        this.feiLieBean = feiLieBean;
        this.context = context;
        this.ye = ye;
        this.options = options;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new Item1ViewHolder(View.inflate(context, R.layout.feilei_neirong1, null));
        }
        return new Item1ViewHolder(View.inflate(context, R.layout.feilei_neirong2, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Item1ViewHolder) {  //feiLieBean.rs.get(ye).children.get(position).dirName
            ((Item1ViewHolder) holder).tv.setText(feiLieBean.rs.get(ye).children.get(position).dirName);
        } else if (holder instanceof Item2ViewHolder) {
            ImageLoader.getInstance().displayImage(feiLieBean.rs.get(ye).children.get(position).children.get(position).imgApp, ((Item2ViewHolder) holder).iv, options);
        }
    }

    @Override
    public int getItemCount() {
        return feiLieBean.rs.get(ye).children.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 0;
        }
        return 1;
    }

    public static class Item1ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public Item1ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.feilei_neirong1);
        }
    }

    //item2 的ViewHolder
    public static class Item2ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;

        public Item2ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.feilei_neirong2);
        }
    }
}

package com.example.wangkuan.honghaizimuying.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangkuan.honghaizimuying.R;
import com.example.wangkuan.honghaizimuying.bean.FeiLieBean;

/**
 * Created by wangkuan on 2016/11/22.
 */
public class FeiLeiBiaoTiAdapter extends RecyclerView.Adapter<FeiLeiBiaoTiAdapter.MyViewHolder> implements View.OnClickListener {

    private FeiLieBean feiLieBean;
    private Context context;
    //定义外部访问的接口
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener
                                               listener) {
        this.mOnItemClickListener = listener;
    }

    //定义接口
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //主要要在点击事件里面得到以下数据
            mOnItemClickListener.onItemClick(view, (String) view.getTag());
        }
    }


    public FeiLeiBiaoTiAdapter(FeiLieBean feiLieBean, Context context) {
        this.feiLieBean = feiLieBean;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.feilei_biaoti, null);
        MyViewHolder vh = new MyViewHolder(inflate);
        inflate.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(feiLieBean.rs.get(position).dirName);
        holder.itemView.setTag(position + "");
    }

    @Override
    public int getItemCount() {
        return feiLieBean.rs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.feilei_biaoti);
        }
    }
}

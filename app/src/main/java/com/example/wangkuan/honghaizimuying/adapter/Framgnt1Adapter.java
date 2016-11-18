package com.example.wangkuan.honghaizimuying.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wangkuan.honghaizimuying.bean.ZhuYeBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * Created by wangkuan on 2016/11/8.
 */
public class Framgnt1Adapter extends PagerAdapter implements View.OnClickListener {

    private ZhuYeBean zhuYe;
    private Context context;
    private DisplayImageOptions options;
    //定义一个访问这个接口的方法
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener
                                               listener) {
        this.mOnItemClickListener = listener;
    }

    public Framgnt1Adapter(ZhuYeBean zhuYe, Context context, DisplayImageOptions options) {
        this.zhuYe = zhuYe;
        this.context = context;
        this.options = options;
    }

    //定义一个接口
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        ImageView iv = new ImageView(context);
        ImageLoader.getInstance().displayImage("http://image1.suning.cn/" + zhuYe.data.get(0).tag.get(position % zhuYe.data.get(0).tag.size()).picUrl, iv, options);
        container.addView(iv);
        //注册成点击事件
        iv.setOnClickListener(this);
        iv.setTag(zhuYe.data.get(0).tag.get(position % zhuYe.data.get(0).tag.size()).linkUrl);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
        //主要要在点击事件里面得到以下数据
            mOnItemClickListener.onItemClick(view, (String) view.getTag());
        }
    }
}

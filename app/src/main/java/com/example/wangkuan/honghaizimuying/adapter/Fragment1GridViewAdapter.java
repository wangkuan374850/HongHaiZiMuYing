package com.example.wangkuan.honghaizimuying.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangkuan.honghaizimuying.R;
import com.example.wangkuan.honghaizimuying.bean.ZhuYeBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by wangkuan on 2016/11/8.
 */
public class Fragment1GridViewAdapter extends BaseAdapter {

    private ZhuYeBean zhuYe;//数据的类
    private Context context;
    private DisplayImageOptions options;

    public Fragment1GridViewAdapter(ZhuYeBean zhuYe, Context context, DisplayImageOptions options) {
        this.zhuYe = zhuYe;
        this.context = context;
        this.options = options;
    }

    @Override
    public int getCount() {
        return zhuYe.data.get(1).tag.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = View.inflate(context, R.layout.fragment1gridviewitem, null);
        ImageView iv = (ImageView) inflate.findViewById(R.id.fragment1_item_tupian);
        TextView tv = (TextView) inflate.findViewById(R.id.fragment1_item_hanzi);
        ImageLoader.getInstance().displayImage("http://image1.suning.cn/" + zhuYe.data.get(1).tag.get(i).picUrl, iv, options);
        tv.setText(zhuYe.data.get(1).tag.get(i).elementName);
        return inflate;
    }
}

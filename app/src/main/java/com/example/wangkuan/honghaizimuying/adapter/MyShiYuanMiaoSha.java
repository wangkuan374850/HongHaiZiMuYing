package com.example.wangkuan.honghaizimuying.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.wangkuan.honghaizimuying.R;
import com.example.wangkuan.honghaizimuying.bean.ZhuYeBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by wangkuan on 2016/11/15.
 */
public class MyShiYuanMiaoSha extends BaseAdapter {
    private Context context;
    private ZhuYeBean zhuYe;
    private DisplayImageOptions options;
    private int kk;

    public MyShiYuanMiaoSha(Context context, ZhuYeBean zhuYe, DisplayImageOptions options, int kk) {
        this.context = context;
        this.zhuYe = zhuYe;
        this.options = options;
        this.kk = kk;
    }

    @Override
    public int getCount() {
        return 6;
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
        View inflate = View.inflate(context, R.layout.shiyuanmiaosha_item, null);
        ImageView iv = (ImageView) inflate.findViewById(R.id.shiyuanmiaosha_tupuan);
        ImageLoader.getInstance().displayImage("http://image1.suning.cn/" + zhuYe.data.get(kk).tag.get(i + 1).picUrl, iv, options);

        return inflate;
    }
}

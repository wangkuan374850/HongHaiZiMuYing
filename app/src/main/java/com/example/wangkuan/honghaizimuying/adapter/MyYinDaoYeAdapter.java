package com.example.wangkuan.honghaizimuying.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wangkuan.honghaizimuying.R;

import java.util.ArrayList;

/**
 * Created by wangkuan on 2016/11/7.
 */
public class MyYinDaoYeAdapter extends PagerAdapter {
    private ArrayList<Integer> ls;
    private Context context;

    public MyYinDaoYeAdapter(ArrayList<Integer> ls, Context context) {
        this.ls = ls;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View inflate = View.inflate(context, R.layout.yindaoye_viewpager, null);

        ImageView iv = (ImageView) inflate.findViewById(R.id.yindao_tupian);
        iv.setImageResource(ls.get(position));
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

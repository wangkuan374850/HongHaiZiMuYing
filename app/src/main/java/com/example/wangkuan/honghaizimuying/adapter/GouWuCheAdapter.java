package com.example.wangkuan.honghaizimuying.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.wangkuan.honghaizimuying.R;
import com.example.wangkuan.honghaizimuying.bean.GouWuCheBean;

import java.util.ArrayList;

/**
 * Created by wangkuan on 2016/11/20.
 */
public class GouWuCheAdapter extends BaseAdapter {

    private ArrayList<GouWuCheBean> chaXun;
    private Context context;
    private TextView qian;

    public GouWuCheAdapter(ArrayList<GouWuCheBean> chaXun, Context context, TextView qian) {
        this.chaXun = chaXun;
        this.context = context;
        this.qian = qian;
    }

    @Override
    public int getCount() {
        return chaXun.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View inflate = View.inflate(context, R.layout.gouwuche_shangpin, null);
        TextView tv1 = (TextView) inflate.findViewById(R.id.gouwuche_tv1);
        TextView tv2 = (TextView) inflate.findViewById(R.id.gouwuche_tv2);
        final CheckBox cb = (CheckBox) inflate.findViewById(R.id.gouwuche_CheckBox);
        tv1.setText(chaXun.get(i).getName());
        tv2.setText(chaXun.get(i).getTuPian() + "");
        cb.setChecked(chaXun.get(i).isXuanZhong());
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chaXun.get(i).setXuanZhong(cb.isChecked());

            }
        });


        return inflate;
    }
}

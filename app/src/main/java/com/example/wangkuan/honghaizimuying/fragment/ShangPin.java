package com.example.wangkuan.honghaizimuying.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangkuan.honghaizimuying.BaiDuJianSuoActivity;
import com.example.wangkuan.honghaizimuying.R;
import com.example.wangkuan.honghaizimuying.adapter.ShangPinViewPager;
import com.example.wangkuan.honghaizimuying.view.MyViewPager;

import java.util.ArrayList;

/**
 * Created by wangkuan on 2016/11/16.
 */
public class ShangPin extends Fragment {

    private MyViewPager vp;
    private ArrayList<Integer> list = new ArrayList<Integer>();
    private String arr = "自营 花王（Merries）妙而舒 婴幼儿纸尿裤 中号M64片（6-11kg） 宝宝尿不湿";
    private TextView biaoTi;
    private ImageView pioJianSuo;
    private TextView diZhiLan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.shangpin, container, false);
        vp = (MyViewPager) inflate.findViewById(R.id.kk);
        biaoTi = (TextView) inflate.findViewById(R.id.shangpin_biaoti);
        pioJianSuo = (ImageView) inflate.findViewById(R.id.shangpin_anNiu1);
        diZhiLan = (TextView) inflate.findViewById(R.id.shangpin_diZhi);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置适配器
        sheZhiShiPeiQi();
        //动态设置字体
        ziTi();
        //百度定位
        jianSuo();


    }

    private void jianSuo() {
        pioJianSuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   startActivity(new Intent(getActivity(), BaiDuJianSuoActivity.class));
                //用回传的方法跳转
                startActivityForResult(new Intent(getActivity(), BaiDuJianSuoActivity.class), 100);
            }
        });

    }

    //得到回传结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (100 == requestCode) {

            if (100 == resultCode) {
                String a = data.getStringExtra("a");
                String b = data.getStringExtra("b");
                String c = data.getStringExtra("c");
                String d = data.getStringExtra("d");
                diZhiLan.setText(a + " " + b + " " + c + " " + d);
                Log.i("aaa", a + "1111111111111111111");
                Log.i("aaa", b + "1111111111111111111");
                Log.i("aaa", c + "1111111111111111111");
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void ziTi() {
        SpannableString sb = new SpannableString(arr);
        sb.setSpan(new BackgroundColorSpan(Color.YELLOW), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        biaoTi.setText(sb);
    }

    private void sheZhiShiPeiQi() {
        list.add(R.mipmap.bg_ptr_car1);
        list.add(R.mipmap.bg_ptr_car1);
        list.add(R.mipmap.bg_ptr_car1);
        list.add(R.mipmap.bg_ptr_car1);
        vp.setAdapter(new ShangPinViewPager(list, getActivity()));
    }
}


package com.example.wangkuan.honghaizimuying.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.wangkuan.honghaizimuying.R;

/**
 * Created by wangkuan on 2016/11/16.
 */
public class XingQing extends Fragment {

    private RadioButton tuWenXingQing;
    private RadioButton guiGeCanShu;
    private RadioButton baoZhuangCanShu;
    private FragmentManager supportFragmentManager;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.xiangqing, container, false);
        tuWenXingQing = (RadioButton) inflate.findViewById(R.id.xianqing_tuWenXingQing);
        guiGeCanShu = (RadioButton) inflate.findViewById(R.id.xianqing_guiGeCanShu);
        baoZhuangCanShu = (RadioButton) inflate.findViewById(R.id.xianqing_baoZhuangCanShu);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //通过事务添加fragment
        tianJiaFragment();
    }

    private void tianJiaFragment() {

        supportFragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.xianqing_frameLayout, new XiangQing_TuWenXiangQing(), "1");
        fragmentTransaction.commit();
        tuWenXingQing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.xianqing_frameLayout, new XiangQing_TuWenXiangQing(), "1");
                fragmentTransaction.commit();
            }
        });
        guiGeCanShu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.xianqing_frameLayout, new XiangQing_GuiGeCanShu(), "2");
                fragmentTransaction.commit();

            }
        });
        baoZhuangCanShu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.xianqing_frameLayout, new XiangQing_BaiZhuangShouHou(), "3");
                fragmentTransaction.commit();
            }
        });

    }
}


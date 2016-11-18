package com.example.wangkuan.honghaizimuying;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.example.wangkuan.honghaizimuying.fragment.FragMent1;
import com.example.wangkuan.honghaizimuying.fragment.FragMent2;
import com.example.wangkuan.honghaizimuying.fragment.FragMent3;

public class XinagQingYeActivity extends FragmentActivity {

    private RadioButton shouYe;
    private RadioButton fenLei;
    private RadioButton gouWuChe;
    private RadioButton woDe;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinag_qing_ye);
        //找控件
        shouYe = (RadioButton) findViewById(R.id.biaoqian_shouye);
        fenLei = (RadioButton) findViewById(R.id.biaoqian_feilei);
        gouWuChe = (RadioButton) findViewById(R.id.biaoqian_gouwuche);
        woDe = (RadioButton) findViewById(R.id.biaoqian_wode);
        //得到管理者
        supportFragmentManager = getSupportFragmentManager();

        //开启事务
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout, new FragMent1(), "0");//添加fragment
        fragmentTransaction.commit();//提交

        shouYe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("aaaaaaaaaaaaa", "1111111111111111111111111111111111111");
                FragmentTransaction fragmentTransaction1 = supportFragmentManager.beginTransaction();
                fragmentTransaction1.replace(R.id.framelayout, new FragMent1(), "1");
                fragmentTransaction1.commit();
            }
        });


        fenLei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.i("aaaaaaaaaaaaa", "2222222222222222222222222222222222222");
                FragmentTransaction fragmentTransaction2 = supportFragmentManager.beginTransaction();
                fragmentTransaction2.replace(R.id.framelayout, new FragMent2(), "2");
                fragmentTransaction2.commit();
            }
        });
        gouWuChe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    Log.i("aaaaaaaaaaaaa", "2222222222222222222222222222222222222");
                FragmentTransaction fragmentTransaction2 = supportFragmentManager.beginTransaction();
                fragmentTransaction2.replace(R.id.framelayout, new FragMent3(), "2");
                fragmentTransaction2.commit();
            }
        });
        woDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(XinagQingYeActivity.this, DengLuActivity.class));
            }
        });


    }

}

package com.example.wangkuan.honghaizimuying;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wangkuan.honghaizimuying.adapter.MyYinDaoYeAdapter;

import java.util.ArrayList;

public class YinDaoYeActivity extends AppCompatActivity {

    private ImageView tiaoGuo;
    private ViewPager viewPager;
    private ImageView jinRu;
    private LinearLayout yuanDian;
    private ArrayList<Integer> ls;
    private SharedPreferences falag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题
        setContentView(R.layout.activity_yin_dao_ye);
        tiaoGuo = (ImageView) findViewById(R.id.yindao_tiaoguo);
        viewPager = (ViewPager) findViewById(R.id.yindao_viewpager);
        jinRu = (ImageView) findViewById(R.id.yindaoye_jinru);
        yuanDian = (LinearLayout) findViewById(R.id.yindaoye_linearlayout);
//得到存储第一次还是第二次进入的那个啥
        falag = getSharedPreferences("falag", MODE_PRIVATE);
        //判断key
        boolean key = falag.getBoolean("key", false);
        if (key) {
            startActivity(new Intent(YinDaoYeActivity.this, XinagQingYeActivity.class));
        } else {

        }

        ls = new ArrayList<Integer>();
        ls.add(R.mipmap.guide_page1);
        ls.add(R.mipmap.guide_page2);
        ls.add(R.mipmap.guide_page3);
        viewPager.setAdapter(new MyYinDaoYeAdapter(ls, getApplicationContext()));

        //动态添加画圆
        for (int i = 0; i < ls.size(); i++) {

            //实例化imagerview
            ImageView iv = new ImageView(YinDaoYeActivity.this);
            if (i == 0) {
                //在drawable里面建立俩xml文件夹
                iv.setImageResource(R.drawable.shen);
            } else {
                iv.setImageResource(R.drawable.qian);
            }
            //设置大小
            LinearLayoutCompat.LayoutParams l = new LinearLayoutCompat.LayoutParams(20, 20);
            l.setMargins(5, 2, 5, 2);
            yuanDian.addView(iv, l);
        }


        //监听事件
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int currentItem = viewPager.getCurrentItem();//得到当前的页面
                if (currentItem == ls.size() - 1) {//判断如果当前的也是最后一页
                    jinRu.setVisibility(View.VISIBLE);//进入按钮显示
                    tiaoGuo.setVisibility(View.GONE);//跳过按钮隐藏

                    // jinRu.setVisibility(View.VISIBLE);
                } else {//相反
                    jinRu.setVisibility(View.GONE);
                    tiaoGuo.setVisibility(View.VISIBLE);
                    // tiaoGuo.setVisibility(View.GONE);
                }
                //遍历数组
                for (int i = 0; i < ls.size(); i++) {
                    //通过线性布局得到里面的孩子
                    ImageView childAt = (ImageView) yuanDian.getChildAt(i);
                    if (i == position) {//判断i==当前=页面的时候默认深色的原点
                        childAt.setImageResource(R.drawable.shen);
                    } else {//相反
                        childAt.setImageResource(R.drawable.qian);
                    }

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //监听进入按钮
        jinRuZhuYe();
    }

    private void jinRuZhuYe() {

        jinRu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(YinDaoYeActivity.this, XinagQingYeActivity.class));
                SharedPreferences.Editor edit = falag.edit();
                edit.putBoolean("key", true);
                edit.commit();
                finish();
            }
        });
        tiaoGuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(ls.size() - 1);
            }
        });
    }
}

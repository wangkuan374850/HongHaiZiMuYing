package com.example.wangkuan.honghaizimuying;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.baidu.mapapi.SDKInitializer;
import com.umeng.analytics.MobclickAgent;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        //  getActionBar().hide();
        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {

                startActivity(new Intent(MainActivity.this, YinDaoYeActivity.class));
                t.cancel();
                finish();
            }
        }, 4000, 1000);

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}

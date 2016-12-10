package com.example.wangkuan.honghaizimuying.util;

import android.app.Application;
import android.util.Log;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by wangkuan on 2016/11/8.
 */
public class ChuShiHua extends Application {
    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone("1105866488", "07iyZhc8vvFYEyyl");
    }
    private static final String TAG = "JPush";
    @Override
    public void onCreate() {
        Log.d(TAG, "[ExampleApplication] onCreate");
        super.onCreate();
        ImageLoaderUtils.initConfiguration(getApplicationContext());
        UMShareAPI.get(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}

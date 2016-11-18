package com.example.wangkuan.honghaizimuying.util;

import android.content.Context;
import android.content.Intent;

import com.example.wangkuan.honghaizimuying.WebViewActivity;

/**
 * Created by wangkuan on 2016/11/14.
 */
public class TiaoZhuanUtil {

    public static void tiaoZhuan(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}

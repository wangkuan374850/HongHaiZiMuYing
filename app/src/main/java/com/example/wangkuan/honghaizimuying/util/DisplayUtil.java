package com.example.wangkuan.honghaizimuying.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by wangkuan on 2016/11/8.
 */
public class DisplayUtil {

    public static DisplayMetrics getAppWidthAndHeight(Context context) {
        // 这个可以用于1.5
        //实例屏幕度量类
        DisplayMetrics dm = new DisplayMetrics();
        //得到系统服务，强转成串口管理者
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        //通过管理者的得到默认显示器,得到度量器
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }
}

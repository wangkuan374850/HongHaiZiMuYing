package com.example.wangkuan.honghaizimuying.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * autour: 王广宽
 * date: 2016/12/5 9:47
 * update: 2016/12/5
 * explain:正则的表达式，判断手机号
 */
public class ZhengZeBiaoDaShi {

    public static boolean getResult(String geShi, String arr) {
        Pattern pattern = Pattern.compile(geShi);//得到放正则表达式的类
        Matcher matcher = pattern.matcher(arr);//放入需要的字符串
        return matcher.matches();//返回结果
    }

}

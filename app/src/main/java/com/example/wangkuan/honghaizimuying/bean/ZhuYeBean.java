package com.example.wangkuan.honghaizimuying.bean;

import java.util.ArrayList;

/**
 * Created by wangkuan on 2016/11/9.
 */
public class ZhuYeBean {

    public ArrayList<MyData> data;

    public class MyData {

        public ArrayList<MyTag> tag;
    }

    public class MyTag {
        public String linkUrl;//url.webview用的
        public String picUrl;//图片
        public String elementName;//名字
    }
}

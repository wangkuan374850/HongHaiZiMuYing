package com.example.wangkuan.honghaizimuying.bean;

import java.util.ArrayList;

/**
 * Created by wangkuan on 2016/11/22.
 */
public class FeiLieBean {
    public ArrayList<MyRs> rs;

    public class MyRs {
        public String dirName;//标题名字
        public ArrayList<MyChildren> children;
    }

    public class MyChildren {
        public String dirName;//详情标题
        public ArrayList<My1Children> children;
    }

    public class My1Children {
        public String dirName;//详情标题
        public String imgApp;//详情图片
    }
}

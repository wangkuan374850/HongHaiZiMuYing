package com.example.wangkuan.honghaizimuying.bean;

/**
 * Created by wangkuan on 2016/11/19.
 */
public class GouWuCheBean {
    private int tuPian;
    private String name;
    private boolean xuanZhong;

    public int getTuPian() {
        return tuPian;
    }

    public void setTuPian(int tuPian) {
        this.tuPian = tuPian;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isXuanZhong() {
        return xuanZhong;
    }

    public void setXuanZhong(boolean xuanZhong) {
        this.xuanZhong = xuanZhong;
    }

    public GouWuCheBean(int tuPian, String name, boolean xuanZhong) {
        this.tuPian = tuPian;
        this.name = name;
        this.xuanZhong = xuanZhong;
    }

    public GouWuCheBean() {
    }
}

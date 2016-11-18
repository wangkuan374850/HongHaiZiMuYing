package com.example.wangkuan.honghaizimuying.bean;

/**
 * Created by wangkuan on 2016/11/8.
 */
public class Fragment1GridView  {
    private int tuPian;
    private String name;

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

    public Fragment1GridView(int tuPian, String name) {
        this.tuPian = tuPian;
        this.name = name;
    }

    public Fragment1GridView() {
    }
}

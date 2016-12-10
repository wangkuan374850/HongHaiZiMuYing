package com.example.wangkuan.honghaizimuying.util;

import java.util.Random;

/**
 * autour: 王广宽
 * date: 2016/12/7 16:21
 * update: 2016/12/7
 * explain:验证码生成的四位验证码
 */
public class SuiJiShu {
    public static String getFourRandom() {
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if (randLength < 4) {
            for (int i = 1; i <= 4 - randLength; i++)
                fourRandom = "0" + fourRandom;
        }
        return fourRandom;
    }
}

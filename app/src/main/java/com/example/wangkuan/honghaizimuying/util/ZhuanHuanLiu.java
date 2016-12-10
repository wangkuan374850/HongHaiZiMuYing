package com.example.wangkuan.honghaizimuying.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wangkuan on 2016/11/22.
 */
public class ZhuanHuanLiu {
    public static String zhuanHuan(InputStream open) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int i = 0;
        byte[] b = new byte[1024];
        while ((i = open.read(b)) != -1) {
            out.write(b, 0, i);
        }
        return out.toString();
    }
}

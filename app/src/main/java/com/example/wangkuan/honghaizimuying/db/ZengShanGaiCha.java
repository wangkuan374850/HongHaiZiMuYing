package com.example.wangkuan.honghaizimuying.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.wangkuan.honghaizimuying.bean.GouWuCheBean;

import java.util.ArrayList;

/**
 * Created by wangkuan on 2016/11/20.
 */
public class ZengShanGaiCha {

    private final ShuJuKu jb;

    public ZengShanGaiCha(Context context) {
        jb = new ShuJuKu(context);
        jb.getWritableDatabase();
    }

    //增加
    public void getzeng(String name, int jiage) {
        //得到刻度可写的数据库
        SQLiteDatabase writableDatabase = jb.getWritableDatabase();
        //增加的语句
        writableDatabase.execSQL("insert into shangPin(name,jiage) values(?,?)", new Object[]{name, jiage});
        //关闭
        writableDatabase.close();
    }

    //删除

    public void getShanChu(int name) {
        SQLiteDatabase writableDatabase = jb.getWritableDatabase();
        writableDatabase.execSQL("delete from shangPin where jiage=?", new Object[]{name});
        writableDatabase.close();
    }

    //查询
    //查询
    ArrayList<GouWuCheBean> ls = new ArrayList<GouWuCheBean>();

    //写一个带返回值的查询方法
    public ArrayList<GouWuCheBean> getChaXun() {
        //得到可读可写的数据库
        SQLiteDatabase writableDatabase = jb.getWritableDatabase();
        //清空集合
        ls.clear();
        //通过这个方法找到光标                             查询所有的数据                                         需要一个String类型的数组
        Cursor cursor = writableDatabase.rawQuery("select * from shangPin", new String[]{});
        while (cursor.moveToNext()) {//让光标移动到下一行
            //通过光标得到每一列的索引值,通过索引值拿到每一列第一行的值，每一次只能拿到一行，所以还需要写一个while
            String name = cursor.getString(cursor.getColumnIndex("name"));
            // String jiage = cursor.getString(cursor.getColumnIndex("jiage"));
            int jiage = cursor.getInt(cursor.getColumnIndex("jiage"));
            //把得到的数据放到集合中
            ls.add(new GouWuCheBean(jiage, name, false));
        }
        //关闭
        cursor.close();
        writableDatabase.close();
        return ls;

    }


}
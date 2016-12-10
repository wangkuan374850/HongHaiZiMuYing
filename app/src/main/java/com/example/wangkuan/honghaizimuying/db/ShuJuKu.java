package com.example.wangkuan.honghaizimuying.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wangkuan on 2016/11/20.
 */
public class ShuJuKu extends SQLiteOpenHelper {
    public ShuJuKu(Context context) {
        super(context, "gouWuChe", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table shangPin (_id integer primary key autoincrement,name varchar(50),jiage Integer )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

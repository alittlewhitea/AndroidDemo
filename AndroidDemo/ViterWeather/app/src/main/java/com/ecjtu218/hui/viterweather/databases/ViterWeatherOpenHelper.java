package com.ecjtu218.hui.viterweather.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by hui on 2016/8/1.
 */
public class ViterWeatherOpenHelper extends SQLiteOpenHelper {

    /**
     * Province表创建语句
     */
    public static final String CREATE_PROVINCE = "create table Province ("
            + "id integer primary key autoincrement, " + "province_name_text,"
            + "province_code text)";

    /**
     * City表创建语句
     */
    public static final String CREATE_CITY = "create table City ("
            + "id integer primary key autoincrement, " + "city_name_text,"
            + "city_code text, "
            + "province_id integer)";
    /**
     * County表创建语句
     */
    public static final String CREATE_County = "create table County ("
            + "id integer primary key autoincrement, " + "county_name_text,"
            + "county_code text, "
            + "county_id integer)";

    /**
     * 在ViterWeatherOpenHelper类下调用onCreate方法执行创建三张表
     */

    public ViterWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_PROVINCE);  //创建Province表
        database.execSQL(CREATE_CITY);      //创建City表
        database.execSQL(CREATE_County);    //创建County表
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }
}


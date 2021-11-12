package com.example.weather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class citydao {
    private dbhelper dbhelper;// 创建databaseHelper对象
    private SQLiteDatabase db;// 创建SQLiteDatabase对象

    public citydao(Context context)// 定义构造函数
    {
        dbhelper = new dbhelper(context);// 初始化databaseHelper对象
    }

    /**
     * 插入城市天气到关注城市中
     * @param city
     */
    public void insert(Lives city){
        String s="insert into city (adcode,province,city,weather,temperature,humidity,reporttime) values ('"+city.getAdcode()+"','"+
                city.getProvince()+"','"+city.getCity()+"','"+city.getWeather()+
                "','"+city.getTemperature()+"','"+city.getHumidity()+"','"+city.getReporttime()+"')";
        db.execSQL(s);
    }

    /**
     * 在关注城市中查询
     * @param a
     * @return
     */
    @SuppressLint("Range")
    public Lives find(String a) {
        db = dbhelper.getWritableDatabase();
        String sql = "select * from city where adcode=?";
        String[] selectionArgs = new String[] { a };
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            Lives d = new Lives();
            d.setAdcode(cursor.getString(cursor.getColumnIndex("adcode")));
            d.setCity(cursor.getString(cursor.getColumnIndex("city")));
            d.setProvince(cursor.getString(cursor.getColumnIndex("province")));
            d.setWeather(cursor.getString(cursor.getColumnIndex("weather")));
            d.setTemperature(cursor.getString(cursor.getColumnIndex("temperature")));
            d.setHumidity(cursor.getString(cursor.getColumnIndex("humidity")));
            d.setReporttime(cursor.getString(cursor.getColumnIndex("reporttime")));
            return d;
        }
        return null;// 没有返回null
    }

    /**
     * 得到所有的关注城市
     * @return
     */
    @SuppressLint("Range")
    public ArrayList<Lives> findall() {
        ArrayList<Lives> list = new ArrayList<Lives>();
        db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("city",null,null,null,null,null,null);
        // 游标从头读到尾
        for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
            Lives d = new Lives();
            d.setAdcode(cursor.getString(cursor.getColumnIndex("adcode")));
            d.setCity(cursor.getString(cursor.getColumnIndex("city")));
            d.setProvince(cursor.getString(cursor.getColumnIndex("province")));
            d.setWeather(cursor.getString(cursor.getColumnIndex("weather")));
            d.setTemperature(cursor.getString(cursor.getColumnIndex("temperature")));
            d.setHumidity(cursor.getString(cursor.getColumnIndex("humidity")));
            d.setReporttime(cursor.getString(cursor.getColumnIndex("reporttime")));
            list.add(d);
        }
        return list;
    }

    /**
     * 删除关注城市
     * @param d
     */
    public void delete(Lives d) {
        db = dbhelper.getWritableDatabase();
        String sql = "delete from city where adcode=?";
        Object bindArgs[] = new Object[] { d.getAdcode() };
        db.execSQL(sql, bindArgs);
    }
    public void close(){
        db = dbhelper.getWritableDatabase();
        if(db != null){
            db.close();
        }
    }

    /**
     * 根据上一级城市id查询所有下级城市
     * @param a
     * @return
     */
    @SuppressLint("Range")
    public ArrayList<city> findallcity(String a) {
        ArrayList<city> list = new ArrayList<city>();
        db = dbhelper.getWritableDatabase();
        String sql = "select * from LocationList where ParentID=?";
        String[] selectionArgs = new String[] { a };
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        // 游标从头读到尾
        for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
            city d = new city();
            d.setFid(cursor.getString(cursor.getColumnIndex("FID")));
            d.setParentid(cursor.getString(cursor.getColumnIndex("ParentID")));
            d.setName(cursor.getString(cursor.getColumnIndex("LocationName")));
            list.add(d);
        }
        return list;
    }

    /**
     * 在缓存的城市中查询
     * @param a
     * @return
     */
    @SuppressLint("Range")
    public Lives hfind(String a) {
        db = dbhelper.getWritableDatabase();
        String sql = "select * from hcity where adcode=?";
        String[] selectionArgs = new String[] { a };
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            Lives d = new Lives();
            d.setAdcode(cursor.getString(cursor.getColumnIndex("adcode")));
            d.setCity(cursor.getString(cursor.getColumnIndex("city")));
            d.setProvince(cursor.getString(cursor.getColumnIndex("province")));
            d.setWeather(cursor.getString(cursor.getColumnIndex("weather")));
            d.setTemperature(cursor.getString(cursor.getColumnIndex("temperature")));
            d.setHumidity(cursor.getString(cursor.getColumnIndex("humidity")));
            d.setReporttime(cursor.getString(cursor.getColumnIndex("reporttime")));
            return d;
        }
        return null;// 没有返回null
    }

    /**
     * 插入到缓存的城市中
     * @param city
     */
    public void hinsert(Lives city){
        String s="insert into hcity (adcode,province,city,weather,temperature,humidity,reporttime) values ('"+city.getAdcode()+"','"+
                city.getProvince()+"','"+city.getCity()+"','"+city.getWeather()+
                "','"+city.getTemperature()+"','"+city.getHumidity()+"','"+city.getReporttime()+"')";
        db.execSQL(s);
    }

    /**
     * 得到所有的缓存结果
     * @return
     */
    @SuppressLint("Range")
    public ArrayList<Lives> hfindall() {
        ArrayList<Lives> list = new ArrayList<Lives>();
        db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("hcity",null,null,null,null,null,null);
        // 游标从头读到尾
        for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
            Lives d = new Lives();
            d.setAdcode(cursor.getString(cursor.getColumnIndex("adcode")));
            d.setCity(cursor.getString(cursor.getColumnIndex("city")));
            d.setProvince(cursor.getString(cursor.getColumnIndex("province")));
            d.setWeather(cursor.getString(cursor.getColumnIndex("weather")));
            d.setTemperature(cursor.getString(cursor.getColumnIndex("temperature")));
            d.setHumidity(cursor.getString(cursor.getColumnIndex("humidity")));
            d.setReporttime(cursor.getString(cursor.getColumnIndex("reporttime")));
            list.add(d);
        }
        return list;
    }

    /**
     * 删除缓存结果
     * @param d
     */
    public void hdelete(Lives d) {
        db = dbhelper.getWritableDatabase();
        String sql = "delete from hcity where adcode=?";
        Object bindArgs[] = new Object[] { d.getAdcode() };
        db.execSQL(sql, bindArgs);
    }
}


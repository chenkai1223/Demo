package com.example.z01.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.z01.bean.PerBean;
import com.example.z01.sql.SqliteHolder;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private final SQLiteDatabase database;

    public UserDao(Context context){
        SqliteHolder holder = new SqliteHolder(context);
        database = holder.getReadableDatabase();
    }


    /**
     * 添加
     * @param ivs
     * @param tes
     * @param tee
     */
    public void add(String ivs,String tes,String tee){
        ContentValues values = new ContentValues();
        values.put("ivs",ivs);
        values.put("tes",tes);
        values.put("tee",tee);
        database.insert("user",null,values);
    }


    public List<PerBean.ResultBean> query(){
        Cursor query = database.query("user", null, null, null, null, null, null);
        List<PerBean.ResultBean> list = new ArrayList<PerBean.ResultBean>();
        while (query.moveToNext()){
            String ivs = query.getString(query.getColumnIndexOrThrow("ivs"));
            String tes = query.getString(query.getColumnIndexOrThrow("tes"));
            String tee = query.getString(query.getColumnIndexOrThrow("tee"));
            PerBean.ResultBean bean = new PerBean.ResultBean(ivs,tes,tee);
            list.add(bean);
        }
        return list;
    }


}

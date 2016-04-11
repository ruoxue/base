package com.ruoxue.basic.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ruoxue.basic.ruoxueUtil.AppUtils;

import java.util.Map;


/**
 * Created by ruoxue_ye on 2016/1/12.
 * @version  1.0
 */
public   class SqUtil<T> {

    public   void save(Context context, Map<String,Object> map) {


           BaseSqlite baseSqlite=new BaseSqlite(context);
        SQLiteDatabase writableDatabase = baseSqlite.getWritableDatabase();
        ContentValues values=new ContentValues();



        for (Map.Entry<String,Object> entry :map.entrySet()){
            values.put(entry.getKey(),entry.getValue().toString());
        }


        writableDatabase.insertOrThrow(AppUtils.getAppName(context),"",values);

    }

}

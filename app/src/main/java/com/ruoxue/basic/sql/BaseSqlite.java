package com.ruoxue.basic.sql;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ruoxue.basic.ruoxueUtil.AppUtils;

/**
 * Created by ruoxue_ye on 2016/1/12.
 */
public class BaseSqlite extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PWD = "pwd";

    public BaseSqlite(Context context) {
        this(context, AppUtils.getAppName(context), null, VERSION);
    }

    public BaseSqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        this(context, name, factory, version, null);
    }

    public BaseSqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(" + ID + " int," + NAME + " varchar(20)," + PWD + "  varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

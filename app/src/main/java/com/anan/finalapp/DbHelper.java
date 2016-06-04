package com.anan.finalapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gtoumie on 04/06/2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "places.db";
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE " + PlacesContract.Places.TABLE_NAME
                        +"("
                        + PlacesContract.Places._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                        + PlacesContract.Places.NAME + " TEXT ,"
                        + PlacesContract.Places.ADDRESS + " TEXT "
                        +")";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + PlacesContract.Places.TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
}


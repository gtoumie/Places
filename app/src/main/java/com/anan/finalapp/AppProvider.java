package com.anan.finalapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.net.ContentHandler;

/**
 * Created by gtoumie on 04/06/2016.
 */
public class AppProvider extends ContentProvider {
    DbHelper dbHelper;
    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return dbHelper.getReadableDatabase().query(PlacesContract.Places.TABLE_NAME, projection, selection, selectionArgs,"","",sortOrder);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rows = dbHelper.getWritableDatabase().insert(PlacesContract.Places.TABLE_NAME,"",values);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return dbHelper.getWritableDatabase().delete(PlacesContract.Places.TABLE_NAME,selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return dbHelper.getWritableDatabase().update(PlacesContract.Places.TABLE_NAME,values,selection, selectionArgs);
    }
}

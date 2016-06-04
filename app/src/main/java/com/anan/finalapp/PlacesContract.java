package com.anan.finalapp;

import android.content.ContentProvider;
import android.net.Uri;

/**
 * Created by gtoumie on 04/06/2016.
 */
public class PlacesContract {
    public final static String AUTHORITY = "com.anan.finalapp.provider";

    public static class Places {
        //table name
        public final static String TABLE_NAME = "places";
        // uri
        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);
        // columns
        public final static String _ID = "_id";
        public final static String NAME = "name";
        public final static String ADDRESS = "address";
    }
}

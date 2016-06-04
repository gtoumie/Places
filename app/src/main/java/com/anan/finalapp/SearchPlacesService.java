package com.anan.finalapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by gtoumie on 04/06/2016.
 */
public class SearchPlacesService extends IntentService {


    public SearchPlacesService() {
        super("SearchPlacesService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String query = intent.getStringExtra(Constants.QUERY_KEY);
        String rawJSON = GoogleAccess.searchPlace(query);
        Log.i(SearchPlacesService.class.getSimpleName(), rawJSON);
    }
}

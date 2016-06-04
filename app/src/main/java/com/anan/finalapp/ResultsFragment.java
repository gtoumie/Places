package com.anan.finalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

/**
 * Created by gtoumie on 04/06/2016.
 */
public class ResultsFragment extends Fragment implements View.OnClickListener, TextView.OnEditorActionListener, LoaderManager.LoaderCallbacks<CursorAdapter> {
    View view;
    Button btnSearch;
    EditText etSearch;
    SwipeRefreshLayout swipeContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Only ever call `setContentView` once right at the top
        view = inflater.inflate(R.layout.fragment_results_list, container, false);

        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync(0);
            }

        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        btnSearch = (Button) view.findViewById(R.id.btnSearch);
        etSearch = (EditText) view.findViewById(R.id.etSearch);

        btnSearch.setOnClickListener(this);
        etSearch.setOnEditorActionListener(this);
        return view;
    }

    public void fetchTimelineAsync(int page) {
        Log.i(ResultsFragment.class.getSimpleName(), "refreshit");
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSearch){
            startPlacesService();
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        startPlacesService();
        return true;
    }

    private void startPlacesService(){
        String searchString = etSearch.getText().toString().trim();
        if(searchString.length()<2){
            Toast.makeText(getActivity(), R.string.search_string_len_error, Toast.LENGTH_SHORT).show();
            return;

        }

        Intent intent= new Intent(getActivity(), SearchPlacesService.class);
        intent.putExtra(Constants.QUERY_KEY,etSearch.getText().toString().trim());
        getActivity().startService(intent);
    }

    @Override
    public Loader<CursorAdapter> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<CursorAdapter> loader, CursorAdapter data) {

    }

    @Override
    public void onLoaderReset(Loader<CursorAdapter> loader) {

    }
}

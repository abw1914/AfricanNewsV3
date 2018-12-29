package com.android.africannewsv3;



import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<ArrayList<NewsData>> {
    ArrayList<NewsData> newsDataArrayList;
    NewsLoader newsLoader;

    public static int LOADER_ID = 0;
    private NewsAdapter newsAdapter;

    public static final String LOG_TOG = MainActivity.class.getSimpleName();
    public static final String NEWS_URL = "https://content.guardianapis.com/search?from-date=2016-01-01&to-date=2018-12-12&q=Africa&api-key=9998be71-d068-4976-b2a1-c69bcc6ed458&show-tags=contributor&page-size=50";

    Bundle bundleForLoader = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoaderCallbacks<ArrayList<NewsData>> callbacks = MainActivity.this;
        getSupportLoaderManager().initLoader(LOADER_ID, bundleForLoader, callbacks);

    }

    @Override
    public Loader<ArrayList<NewsData>> onCreateLoader(int i, Bundle bundle) {
        //Log.e(LOG_TOG, "Loader OnCreateLoader method call... ");
        return new NewsLoader(this, NEWS_URL);
    }



    @Override
    public void onLoadFinished(Loader<ArrayList<NewsData>> loader, ArrayList<NewsData> newsDataArrayList) {
        Log.e(LOG_TOG, "onLoadFinished method call... ");
        updateUi(newsDataArrayList);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<NewsData>> loader) {
        Log.e(LOG_TOG, "onLoaderReset method call... ");
        updateUi(newsDataArrayList);
    }


    private void updateUi(final ArrayList<NewsData> newsDataArrayList) {
        ListView newsListView = findViewById(R.id.list);
        final NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this, 0, newsDataArrayList);
        newsListView.setAdapter(newsAdapter);
    }
}


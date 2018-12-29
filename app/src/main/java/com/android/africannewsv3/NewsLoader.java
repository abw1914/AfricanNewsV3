package com.android.africannewsv3;


import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.support.v4.app.LoaderManager;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class NewsLoader extends AsyncTaskLoader<ArrayList<NewsData>> {

    //public static final String NEWS_URL = "https://content.guardianapis.com/search?from-date=2016-01-01&to-date=2018-12-12&q=Africa&api-key=9998be71-d068-4976-b2a1-c69bcc6ed458&show-tags=contributor&page-size=50";
    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }



    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override

    public ArrayList<NewsData> loadInBackground() {
        if(mUrl == null) {
            return null;
        }
        ArrayList<NewsData> newsDataArrayList = null;
        try {
            newsDataArrayList = NetworkUtils.fetchNewsData(mUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsDataArrayList;
    }
}

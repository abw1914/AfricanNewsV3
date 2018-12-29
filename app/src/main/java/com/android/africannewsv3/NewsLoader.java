package com.android.africannewsv3;


import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.support.v4.app.LoaderManager;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class NewsLoader extends AsyncTaskLoader<ArrayList<NewsData>> {

    public static final String NEWS_URL = "https://content.guardianapis.com/search?from-date=2016-01-01&to-date=2018-12-12&q=Africa&api-key=9998be71-d068-4976-b2a1-c69bcc6ed458&show-tags=contributor&page-size=50";


    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
    }

    @Override

    public ArrayList<NewsData> loadInBackground() {
        ArrayList<NewsData> newsDataArrayList = null;
        try {
            NetworkUtils.fetchNewsData(NEWS_URL);
            URL url = NetworkUtils.createUrl(NEWS_URL);
            String jsonResponse = NetworkUtils.makeHttpRequest(url);
            newsDataArrayList = NetworkUtils.extractDataFromJson(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newsDataArrayList;
    }
}

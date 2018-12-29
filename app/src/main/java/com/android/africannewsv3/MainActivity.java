package com.android.africannewsv3;


import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<NewsData>> {
    ArrayList<NewsData> newsDataArrayList;

    public static int LOADER_ID = 0;

    public static final String LOG_TOG = MainActivity.class.getSimpleName();
    //public static final String NEWS_URL = "https://content.guardianapis.com/search?from-date=2016-01-01&to-date=2018-12-12&q=Africa&api-key=9998be71-d068-4976-b2a1-c69bcc6ed458&show-tags=contributor&page-size=50";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();

        //new GetNewsResults().execute(NEWS_URL);
    }

   /* private class GetNewsResults extends AsyncTask<String, Void, ArrayList<NewsData>> {


        @Override
        protected ArrayList<NewsData> doInBackground(String... strings) {
            if(strings.length < 1 || strings[0] == null) {
                return null;
            }
            ArrayList<NewsData> newsDataArrayList = null;
            try {
                newsDataArrayList = NetworkUtils.fetchNewsData(strings[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e(LOG_TOG, "Do in background async task executing...");
            return newsDataArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<NewsData> newsDataArrayList) {
            if(newsDataArrayList == null) {
                return;
            }
            updateUi(newsDataArrayList);
            Toast.makeText(MainActivity.this,
                    "News Data is Loading...", Toast.LENGTH_LONG).show();
        }
*/


    @Override
    public android.support.v4.content.Loader<ArrayList<NewsData>> onCreateLoader(int i, Bundle bundle) {

        Log.e(LOG_TOG, "Loader OnCreateLoader method call... ");
        return new NewsLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<ArrayList<NewsData>> loader, ArrayList<NewsData> data) {
        Log.e(LOG_TOG, "onLoadFinished method call... ");
        updateUi(newsDataArrayList);
    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<ArrayList<NewsData>> loader) {
        Log.e(LOG_TOG, "onLoaderReset method call... ");
        updateUi(newsDataArrayList);
    }

    private void updateUi(final ArrayList<NewsData> newsDataArrayList) {
        ListView newsListView = findViewById(R.id.list);
        final NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this, 0, newsDataArrayList);
        newsListView.setAdapter(newsAdapter);
    }
}


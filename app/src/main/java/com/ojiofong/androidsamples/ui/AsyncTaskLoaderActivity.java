package com.ojiofong.androidsamples.ui;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.model.GithubPojo;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ojiofong on 9/14/16.
 * ...
 */

public class AsyncTaskLoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<GithubPojo> {

    private static final String TAG = AsyncTaskLoaderActivity.class.getSimpleName();
    private static final int LOADER_ID = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        Log.d(TAG, "oncreate done ");
    }

    private static String getFromWeb() {
        try {
            String url = "https://api.github.com/search/users?q=tom";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Loader<GithubPojo> onCreateLoader(int id, Bundle args) {
        return new MyAsyncTaskLoader(AsyncTaskLoaderActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<GithubPojo> loader, GithubPojo data) {

        StringBuilder sb = new StringBuilder();
        for(GithubPojo.Item item : data.getItems()){
            sb.append(item.getLogin()).append("\n");
        }

        ((TextView) findViewById(R.id.tvHelloWorld)).setText(sb.toString());
    }

    @Override
    public void onLoaderReset(Loader<GithubPojo> loader) {

    }

    private static class MyAsyncTaskLoader extends AsyncTaskLoader<GithubPojo> {

        public MyAsyncTaskLoader(Context context) {
            super(context);
            forceLoad();
            Log.d(TAG, "constructor of MyAsyncTaskLoader ");
        }

        @Override
        public GithubPojo loadInBackground() {

            Log.d(TAG, "start loadInBackground");

            String result = getFromWeb();

            Type type = new TypeToken<GithubPojo>() {
            }.getType();
            GithubPojo pojo = new Gson().fromJson(result, type);

            Log.d(TAG, "end loadInBackground");

            return pojo;
        }

        @Override
        public void deliverResult(GithubPojo data) {
            super.deliverResult(data);
            Log.d(TAG, "deliverResult");
            Log.d(TAG, data.getItems().toString());
        }
    }
}

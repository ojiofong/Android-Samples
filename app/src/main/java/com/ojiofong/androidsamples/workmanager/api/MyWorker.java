package com.ojiofong.androidsamples.workmanager.api;

import android.support.annotation.NonNull;
import android.util.Log;

import com.ojiofong.androidsamples.workmanager.model.RepoResponse;

import java.io.IOException;
import java.util.List;

import androidx.work.Data;
import androidx.work.Worker;

/**
 * Created by ojiofong on 5/27/18.
 * .
 */

public class MyWorker extends Worker {

    private static final String TAG = MyWorker.class.getSimpleName();

    @NonNull
    @Override
    public WorkerResult doWork() {

        // do background work here

        String retValue = null;

        try {
            Log.d(TAG, "waiting for work to complete");
            // Execute synchronously
            List<RepoResponse> body = Api.getGithubApi().getRepos("ojiofong").execute().body();
            if (body != null) retValue = body.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Optionally return a value if you'd like 10 kb max payload
        Data data = new Data.Builder()
                .putString("key1", "retValue")
                .build();
        setOutputData(data);

        Log.d(TAG, "completed work-> " + retValue);
        return WorkerResult.SUCCESS;
    }
}

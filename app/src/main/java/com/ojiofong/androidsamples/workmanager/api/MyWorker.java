package com.ojiofong.androidsamples.workmanager.api;

import android.content.Context;
import android.util.Log;

import com.ojiofong.androidsamples.workmanager.model.RepoResponse;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * Created by ojiofong on 5/27/18.
 * .
 */

public class MyWorker extends Worker {

    private static final String TAG = MyWorker.class.getSimpleName();

    public MyWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {

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
        Data outputData = new Data.Builder()
                .putString("key1", "retValue")
                .build();

        Log.d(TAG, "completed work-> " + retValue);
        return Result.success(outputData);
    }
}

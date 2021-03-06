package com.ojiofong.androidsamples.workmanager.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ojiofong.androidsamples.workmanager.api.MyWorker;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

/**
 * Created by ojiofong on 5/27/18.
 * .
 */

public class WorkViewModel extends AndroidViewModel {

    // Can set constraints to the work request if you'd like
    Constraints constraints = new Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .build();

    public final WorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
            .setConstraints(constraints)
            .addTag("optional_tag_here")
            .build();

    public final WorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 15, TimeUnit.MINUTES)
            .addTag("optional_tag_here")
            .build();

    public WorkViewModel(@NonNull Application application) {
        super(application);
    }

    public void doSingleWork(){
        WorkManager.getInstance().enqueue(oneTimeWorkRequest);
    }


}

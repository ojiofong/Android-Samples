package com.ojiofong.androidsamples.workmanager.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.workmanager.viewmodel.WorkViewModel;

import androidx.work.WorkManager;
import androidx.work.WorkStatus;

/**
 * Created by ojiofong on 5/27/18.
 */

public class WorkManagerActivity extends AppCompatActivity {

    public final String TAG = WorkManagerActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadpool);
        WorkViewModel workViewModel = ViewModelProviders.of(this).get(WorkViewModel.class);
        workViewModel.doSingleWork();
        WorkManager.getInstance().getStatusById(workViewModel.oneTimeWorkRequest.getId()).observe(this, new Observer<WorkStatus>() {
            @Override
            public void onChanged(@Nullable WorkStatus workStatus) {
                if (workStatus != null) {
                    updateUI(workStatus.getState().name());
                }
            }
        });
    }

    private void updateUI(String msg) {
        ((TextView) findViewById(R.id.text)).setText(msg);
    }
}

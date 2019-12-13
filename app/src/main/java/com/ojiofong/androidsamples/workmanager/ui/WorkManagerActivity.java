package com.ojiofong.androidsamples.workmanager.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.workmanager.viewmodel.WorkViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

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
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workViewModel.oneTimeWorkRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(@Nullable WorkInfo workInfo) {
                if (workInfo != null) {
                    updateUI(workInfo.getState().name());
                }
            }
        });
    }

    private void updateUI(String msg) {
        ((TextView) findViewById(R.id.text)).setText(msg);
    }
}

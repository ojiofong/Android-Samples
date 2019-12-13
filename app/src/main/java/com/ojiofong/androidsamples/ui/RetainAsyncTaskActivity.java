package com.ojiofong.androidsamples.ui;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.fragment.TaskRetainFragment;

public class RetainAsyncTaskActivity extends AppCompatActivity implements TaskRetainFragment.Callbacks {
    private static final String TAG = RetainAsyncTaskActivity.class.getSimpleName();
    private static final String TAG_FRAGMENT = "tag_fragment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadpool);
        setTitle(TAG);

        FragmentManager fm = getSupportFragmentManager();
        TaskRetainFragment taskRetainFragment = (TaskRetainFragment) fm.findFragmentByTag(TAG_FRAGMENT);
        if (taskRetainFragment == null) {
            taskRetainFragment = TaskRetainFragment.newInstance("Awesome input");
            fm.beginTransaction().add(taskRetainFragment, TAG_FRAGMENT).commit();
        }

    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onProgressUpdate(int percent) {
        ((TextView) findViewById(R.id.text)).setText("onProgressUpdate " + percent);
    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onPostExecute() {

    }
}

package com.ojiofong.androidsamples.threadpool;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ojiofong.androidsamples.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class ThreadPoolActivity extends AppCompatActivity {

    private static final String TAG = ThreadPoolActivity.class.getSimpleName();
    /*
     * Gets the number of available cores
     * (not always the same as the maximum number of cores)
     */
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    // Sets the amount of time an idle thread waits before terminating
    private static final int KEEP_ALIVE_TIME = 1000;

    // Sets the Time Unit to Milliseconds
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.MILLISECONDS;

    // Used to update UI with work progress
    private int count = 0;

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            // Do some work
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Update the UI with progress
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    count++;
                    String msg = count < 100 ? "working " : "done ";
                    updateStatus(msg + count);
                }
            });

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadpool);
        setTitle(TAG);
       // init();
    }

    // button click - performs work on a single thread
    public void buttonClickSingleThread(View view) {
        count = 0;
        Executor mSingleThreadExecutor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100; i++) {
            mSingleThreadExecutor.execute(mRunnable);
        }
    }

    // button click - performs work using a thread pool
    public void buttonClickThreadPool(View view) {
        count = 0;
        ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(
                NUMBER_OF_CORES + 5,   // Initial pool size
                NUMBER_OF_CORES + 8,   // Max pool size
                KEEP_ALIVE_TIME,       // Time idle thread waits before terminating
                KEEP_ALIVE_TIME_UNIT,  // Sets the Time Unit for KEEP_ALIVE_TIME
                new LinkedBlockingDeque<Runnable>());  // Work Queue

        for (int i = 0; i < 100; i++) {
            mThreadPoolExecutor.execute(mRunnable);
        }
    }

    private void updateStatus(String msg) {
        ((TextView) findViewById(R.id.text)).setText(msg);
    }

}

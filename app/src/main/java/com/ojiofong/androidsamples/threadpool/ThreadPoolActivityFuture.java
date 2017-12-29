package com.ojiofong.androidsamples.threadpool;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.ui.BaseActivity;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class ThreadPoolActivityFuture extends BaseActivity {

    private static final String TAG = ThreadPoolActivityFuture.class.getSimpleName();
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

    private Callable<Integer> mCallable = new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(50);
                count = count + 1;
                updateUI();
            }
            return count;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadpool);
        setTitle(TAG);
    }

    // button click - performs work on a single thread
    public void buttonClickSingleThread(View view) {
        count = 0;
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(mCallable);
    }

    // button click - performs work using a thread pool
    public void buttonClickThreadPool(View view) throws InterruptedException {
        count = 0;
        ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(
                NUMBER_OF_CORES + 5,   // Initial pool size
                NUMBER_OF_CORES + 8,   // Max pool size
                KEEP_ALIVE_TIME,       // Time idle thread waits before terminating
                KEEP_ALIVE_TIME_UNIT,  // Sets the Time Unit for KEEP_ALIVE_TIME
                new LinkedBlockingDeque<Runnable>());  // Work Queue

        Future<Integer> future = mThreadPoolExecutor.submit(mCallable);
        Thread.sleep(200);
        future.cancel(true);
    }

    private void updateUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String msg = count < 100 ? "working " : "done ";
                msg = count + " " + msg;
                ((TextView) findViewById(R.id.text)).setText(msg);
            }
        });
    }

}

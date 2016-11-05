package com.ojiofong.androidsamples.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by ojiofong on 11/5/16.
 */

public class TaskRetainFragment extends Fragment {
    private static final String TAG = TaskRetainFragment.class.getSimpleName();
    private MyTask myTask;

    public TaskRetainFragment() {
    }

    public static TaskRetainFragment newInstance(String input) {
        TaskRetainFragment fragment = new TaskRetainFragment();
        Bundle b = new Bundle();
        b.putString("key", input);
        fragment.setArguments(b);
        return fragment;
    }

    public Callbacks mCallback;

    public interface Callbacks {
        void onPreExecute();

        void onProgressUpdate(int percent);

        void onCancelled();

        void onPostExecute();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (Callbacks) context;
        } catch (ClassCastException e) {
            Log.e(TAG, context + " must implement " + Callbacks.class.getSimpleName());
        }
    }

    /**
     * Set callback to null to avoid leaking Activity instance.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    /**
     * Called once when the retained Fragment is first created.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        String input = getArguments().getString("key", "default value here");
        myTask = new MyTask();
        myTask.execute(input);
        //myTask.cancel(true);
    }

    /**
     * Not called in orientation change
     * Called once when finally destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        myTask.cancel(true);
        myTask = null;
    }

    /**
     * Note - check if the mCallBack is null in each
     * method in case called after onDestroy().
     */
    private class MyTask extends AsyncTask<String, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mCallback != null) mCallback.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            Log.d(TAG, "background input params-> " + params[0]);
            for (int i = 1; i <= 100 && !isCancelled(); i++) {
                SystemClock.sleep(1000);
                publishProgress(i);
            }
            return null;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (mCallback != null) mCallback.onProgressUpdate(values[0]);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            if (mCallback != null) mCallback.onCancelled();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (mCallback != null) mCallback.onPostExecute();
        }
    }


}

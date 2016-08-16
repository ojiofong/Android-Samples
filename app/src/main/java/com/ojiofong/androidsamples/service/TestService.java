package com.ojiofong.androidsamples.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class TestService extends Service {

    private static final String TAG = TestService.class.getSimpleName();
    public static final int SAY_HELLO = 1;

    /**
     * interface for clients that bind
     */
    private IBinder mBinder = new MyBinder();
    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    public Messenger messenger;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    Log.d(TAG, "Doing some work in worker thread");
                    Looper.prepare();
                    messenger = new Messenger(new IncomingHander(getApplicationContext()));
                    Looper.loop();
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        for (int i = 0; i < 3; i++) {
            Log.d(TAG, "onStartCommand doing some work");
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public String getSomeData(int... a) {

        if(a.length == 2){
            return "Did addition " +(a[0] + a[1]) + " from service";
        }

        return "Got some data from service";
    }


    public class MyBinder extends Binder {
        // Return this instance of TestService so clients can call public methods
        public TestService getService() {
            return TestService.this;
        }
    }

    /**
     * Handler of incoming messages from clients.
     */
    static class IncomingHander extends Handler {
        Context context;

        IncomingHander(Context context) {
            this.context = context;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SAY_HELLO:
                    Toast.makeText(context, "Hello Message", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    }


}

package com.ojiofong.androidsamples.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.ojiofong.androidsamples.R;

/**
 * Created by ojiofong on 9/16/16.
 * ..
 */

public class InputDetectionActivity extends AppCompatActivity {
    private static final String TAG = InputDetectionActivity.class.getSimpleName();

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = intent.getIntExtra("state", -1);
                switch (state) {
                    case 0:
                        Log.d(TAG, "Headset is unplugged");
                        ((TextView) findViewById(R.id.tvHelloWorld)).setText("Headset is unplugged");
                        break;
                    case 1:
                        Log.d(TAG, "Headset is plugged");
                        ((TextView) findViewById(R.id.tvHelloWorld)).setText("Headset is plugged");
                        break;
                    default:
                        Log.d(TAG, "I have no idea what the headset state is");
                        ((TextView) findViewById(R.id.tvHelloWorld)).setText("I have no idea what the headset state is");
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerBroadcast();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unRegisterBroadcast();
    }

    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(myReceiver, filter);
    }

    private void unRegisterBroadcast() {
        unregisterReceiver(myReceiver);
    }
}

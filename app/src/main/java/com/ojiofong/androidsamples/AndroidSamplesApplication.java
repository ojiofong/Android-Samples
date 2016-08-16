package com.ojiofong.androidsamples;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.webrtc.PeerConnectionFactory;

/**
 * Created by ojiofong on 7/13/16.
 */

public class AndroidSamplesApplication extends Application {
    private static final String TAG = AndroidSamplesApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        initWebRTC(this);
    }

    private void initWebRTC(Context context){
        boolean connState = PeerConnectionFactory.initializeAndroidGlobals(context, true, true, true, null);
        Log.d(TAG, "WebRTC PeerConnectionFactory state "+ connState);
        Toast.makeText(context, "WebRTC Connection state "+ connState, Toast.LENGTH_LONG).show();
    }
}

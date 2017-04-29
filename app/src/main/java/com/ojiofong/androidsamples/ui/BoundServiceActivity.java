package com.ojiofong.androidsamples.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.service.TestService;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class BoundServiceActivity extends AppCompatActivity {
    private static final String TAG = BoundServiceActivity.class.getSimpleName();

    private TestService mTestService;
    private Messenger mMessanger;
    private boolean mBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected");
            TestService.MyBinder myBinder = (TestService.MyBinder) iBinder;
            mTestService = myBinder.getService();
            mMessanger = mTestService.messenger;
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected");
            mBound = false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boundservice);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unBindFromTestService();
    }

    private boolean isBound(){
        if(!mBound){
            Toast.makeText(this, "Service is not bound", Toast.LENGTH_SHORT).show();
        }
        return mBound;
    }


    @OnClick(R.id.start_service)
    public void startTestService() {
        startService(new Intent(this, TestService.class));
    }

    @OnClick(R.id.stop_service)
    public void stopTestService() {
        stopService(new Intent(this, TestService.class));
    }

    @OnClick(R.id.bind_to_service)
    public void bindToTestService() {
        Intent intent = new Intent(this, TestService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @OnClick(R.id.unbind_from_service)
    public void unBindFromTestService() {
        if (mBound) {
            unbindService(serviceConnection);
            mBound = false;
        }
    }

    @OnClick(R.id.get_data_from_service)
    public void getDataFromService() {
        if (isBound()) {
            String data = mTestService.getSomeData(5,7);
            Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.send_msg_to_service)
    public void sendMessageToService() {
        if (isBound()){
            Message msg = Message.obtain(null, TestService.SAY_HELLO, 0, 0);
            msg.replyTo = messengerForActivityHandler;
            try {
                mMessanger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // To receive messages on the activity
    private class ActivityHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(BoundServiceActivity.this, "Activity: handleMessage " + msg.obj, Toast.LENGTH_SHORT).show();
        }
    }
    Messenger messengerForActivityHandler = new Messenger(new ActivityHandler());
}

package com.ojiofong.androidsamples.ui;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ojiofong.androidsamples.R;

import java.util.Set;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <uses-permission android:name="android.permission.BLUETOOTH"/>
 * <uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
 * <!--ACCESS_COARSE_LOCATION is required for Bluetooth.Action_Found in API 23-->
 *  <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
 */
public class BluetoothActivity extends AppCompatActivity {
    private static final String TAG = BluetoothActivity.class.getSimpleName();
    private BluetoothAdapter mBluetoothAdapter;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            /**
             * Called whenever a new bluetooth device is found
             * You should probably add the name and address to an array adapter to show in a ListView
             * */
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String info = device.getName() + " / " + device.getAddress();
                Log.d(TAG, info);
                showToast(info);
            }

            Log.d(TAG, action);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        setTitle("Bluetooth Example");
        ButterKnife.bind(this);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        /**
         * if mBluetoothAdapter is null, Device does not support bluetooth
         * So be sure to disable bluetooth features gracefully
         * */
        if (mBluetoothAdapter == null) showToast("Device does not support bluetooth");


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterMyReceiver();
    }

    @OnClick(R.id.bluetooth_enable)
    public void bluetoothEnable() {
        mBluetoothAdapter.enable();
    }

    @OnClick(R.id.bluetooth_on)
    public void bluetoothOn() {
        if (!mBluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 0);
            showToast("Bluetooth on");

        } else {
            showToast("Bluetooth is already on");
        }
    }

    @OnClick(R.id.bluetooth_off)
    public void bluetoothOff() {

        mBluetoothAdapter.disable();
        showToast("Bluetooth off");
    }

    @OnClick(R.id.bluetooth_discoverable)
    public void bluetoothDiscoverable() {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(intent, 0);
        showToast("Making device disccoverable");
    }

    @OnClick(R.id.bluetooth_paired_devices)
    public void bluetoothPairedDevices() {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        StringBuffer buffer = new StringBuffer();
        for (BluetoothDevice device : pairedDevices) {
            // Add the name and address to maybe an array adapter to show in a ListView
            buffer.append(device.getName() + " / " + device.getAddress() + "\n");
        }
        showToast(buffer.toString());
    }


    @OnClick(R.id.bluetooth_discover_devices)
    public void searchForBluetoothDevices() {
        // Result is returned via BroadcastReceiver
        registerMyReceiver();
        if (mBluetoothAdapter.isDiscovering()) mBluetoothAdapter.cancelDiscovery();
        if (mBluetoothAdapter.startDiscovery()) showToast("started search/discovery");
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void registerMyReceiver() {
        IntentFilter bluetoothFilter = new IntentFilter();
        bluetoothFilter.addAction(BluetoothDevice.ACTION_FOUND);
        bluetoothFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        bluetoothFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(receiver, bluetoothFilter);
    }

    private void unRegisterMyReceiver() {
        unregisterReceiver(receiver);
    }


}

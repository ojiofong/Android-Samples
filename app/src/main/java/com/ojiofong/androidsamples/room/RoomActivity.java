package com.ojiofong.androidsamples.room;

import android.os.Bundle;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.ui.BaseActivity;

public class RoomActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        setTitle(R.string.mvvm_room_live_data);
    }
}

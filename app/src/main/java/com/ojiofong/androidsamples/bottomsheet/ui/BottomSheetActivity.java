package com.ojiofong.androidsamples.bottomsheet.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ojiofong.androidsamples.R;

/**
 * Created by ojiofong on 3/9/18.
 */

public class BottomSheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyBottomSheetFragment.showWith(this);
    }
}

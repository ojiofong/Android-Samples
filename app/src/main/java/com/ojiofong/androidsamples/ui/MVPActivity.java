package com.ojiofong.androidsamples.ui;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ojiofong.androidsamples.R;
import com.ojiofong.androidsamples.mvp.Mvp;

/**
 *
 */
public class MVPActivity extends AppCompatActivity implements Mvp.IView {
    private static final String TAG = MVPActivity.class.getSimpleName();

    Mvp.PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        setTitle(TAG);
        presenter = new Mvp.PresenterImpl(this);
    }

    public void onClick(View view) {
        presenter.doChangeColor();
    }

    @Override
    public void onChangeColor(int color) {
        findViewById(R.id.id_layout_Sensor).setBackgroundColor(ContextCompat.getColor(this, color));
    }
}

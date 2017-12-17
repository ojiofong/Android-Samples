package com.ojiofong.androidsamples.dagger.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ojiofong.androidsamples.dagger.component.DaggerVehicleComponent;
import com.ojiofong.androidsamples.dagger.component.VehicleComponent;
import com.ojiofong.androidsamples.dagger.model.Vehicle;
import com.ojiofong.androidsamples.dagger.module.VehicleModule;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {
    private static final String TAG = DaggerActivity.class.getSimpleName();

    VehicleComponent component;

    @Inject
    Vehicle vehicle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        component = DaggerVehicleComponent.builder().vehicleModule(new VehicleModule()).build();
        component.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        vehicle.increaseSpead(100);
        Toast.makeText(this, "" + vehicle.getSpeed(), Toast.LENGTH_SHORT).show();
    }
}

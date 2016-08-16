package com.ojiofong.androidsamples.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ojiofong.androidsamples.dagger.component.DaggerVehicleComponent;
import com.ojiofong.androidsamples.dagger.component.VehicleComponent;
import com.ojiofong.androidsamples.dagger.model.Vehicle;
import com.ojiofong.androidsamples.dagger.module.VehicleModule;

public class DaggerActivity extends AppCompatActivity {
    private static final String TAG = DaggerActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_animation);

        VehicleComponent component = DaggerVehicleComponent.builder().vehicleModule(new VehicleModule()).build();
        VehicleComponent c = DaggerVehicleComponent.builder().vehicleModule(new VehicleModule()).build();

        Vehicle vehicle = component.providesVehicle();
        vehicle.increaseSpead(100);

        Toast.makeText(this, String.valueOf(vehicle.getSpeed()), Toast.LENGTH_SHORT).show();

    }



}

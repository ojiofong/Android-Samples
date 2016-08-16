package com.ojiofong.androidsamples.dagger.component;


import com.ojiofong.androidsamples.dagger.model.Vehicle;
import com.ojiofong.androidsamples.dagger.module.VehicleModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ojiofong on 6/8/16.
 * ..
 */

@Singleton
@Component(modules = {VehicleModule.class})
public interface VehicleComponent {

    Vehicle providesVehicle();
}

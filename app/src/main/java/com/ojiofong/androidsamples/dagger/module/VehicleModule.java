package com.ojiofong.androidsamples.dagger.module;

import com.ojiofong.androidsamples.dagger.model.Motor;
import com.ojiofong.androidsamples.dagger.model.Vehicle;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ojiofong on 6/8/16.
 * ..
 */

@Module
public class VehicleModule {

    @Provides @Singleton
    Motor provideMotor(){
        return new Motor();
    }

    @Provides @Singleton
    Vehicle provideVehicle(){
        return new Vehicle(new Motor());
    }


}

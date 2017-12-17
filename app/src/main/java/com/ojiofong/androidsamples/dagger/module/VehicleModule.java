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
    Motor providesMotor(){
        return new Motor();
    }

    @Provides @Singleton
    Vehicle providesVehicle(Motor motor){
        return new Vehicle(motor);
    }


}

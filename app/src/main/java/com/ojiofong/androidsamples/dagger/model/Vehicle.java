package com.ojiofong.androidsamples.dagger.model;

import javax.inject.Inject;

/**
 * Created by ojiofong on 6/8/16.
 * ..
 */

public class Vehicle {
    private Motor motor;

    @Inject
    public Vehicle(Motor motor){
        this.motor = motor;
    }

    public void increaseSpead(int value){
        motor.accelerate(value);
    }

    public void stop(){
        motor.brake();
    }

    public int getSpeed(){
        return motor.getRpm();
    }
}

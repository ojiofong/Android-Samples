package com.ojiofong.androidsamples.dagger.model;

/**
 * Created by ojiofong on 6/8/16.
 * ..
 */

public class Vehicle {
    private Motor motor;

    public Vehicle(Motor motor) {
        this.motor = motor;
    }

    public void increaseSpead(int value) {
        motor.accelerate(value);
    }

    public void stop() {
        motor.brake();
    }

    public int getSpeed() {
        return motor.getRpm();
    }
}

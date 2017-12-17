package com.ojiofong.androidsamples.dagger.component;


import com.ojiofong.androidsamples.dagger.module.VehicleModule;
import com.ojiofong.androidsamples.dagger.ui.DaggerActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ojiofong on 6/8/16.
 * ..
 */

@Singleton
@Component(modules = {VehicleModule.class})
public interface VehicleComponent {
    void inject(DaggerActivity daggerActivity);
}

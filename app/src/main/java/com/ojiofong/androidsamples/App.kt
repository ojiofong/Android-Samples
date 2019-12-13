package com.ojiofong.androidsamples

import android.app.Application
import com.ojiofong.androidsamples.koin.di.allModules
import org.koin.android.ext.android.startKoin

class App : Application() {

    var display = ""

    companion object {
        private val TAG = App::class.java.simpleName
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, allModules)
    }

}
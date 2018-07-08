package com.ojiofong.androidsamples.koin.model

import com.ojiofong.androidsamples.koin.di.DI

/**
 * Mock App level object to inject
 * */
data class MyAppManager(val data: String)

/**
 * Mock User level object to inject depends on the App level object
 * */
data class MyUserManager(val data: String, val appManager: MyAppManager) {
    //    val displayStuff = DI.get(App::class.java).display
    val displayStuff = DI.get(MyRandomModel::class.java).data
}

/**
 * Mock random string
 * */
data class MyRandomModel(var data: String = "default MyRandomModel")
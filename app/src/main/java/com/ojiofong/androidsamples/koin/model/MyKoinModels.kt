package com.ojiofong.androidsamples.koin.model

/**
 * Mock App level object to inject
 * */
data class MyAppManager(val data: String)

/**
 * Mock User level object to inject depends on the App level object
 * */
data class MyUserManager(val data: String, val appManager: MyAppManager)
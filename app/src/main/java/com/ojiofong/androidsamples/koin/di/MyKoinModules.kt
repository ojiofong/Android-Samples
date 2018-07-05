package com.ojiofong.androidsamples.koin.di

import com.ojiofong.androidsamples.App
import com.ojiofong.androidsamples.koin.model.MyAppManager
import com.ojiofong.androidsamples.koin.model.MyUserManager
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val allModules: List<Module> get() = listOf(appModule, userModule)

private val appModule: Module = applicationContext {
    bean { get() as App }
    factory { MyAppManager("Awesome MyAppManager Injection") }
}

private val userModule: Module = applicationContext {
    factory { MyUserManager("Sweet MyUserManager Injection", get()) }
}
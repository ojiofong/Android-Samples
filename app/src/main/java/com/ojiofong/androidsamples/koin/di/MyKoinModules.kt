package com.ojiofong.androidsamples.koin.di

import com.ojiofong.androidsamples.koin.model.MyAppManager
import com.ojiofong.androidsamples.koin.model.MyRandomModel
import com.ojiofong.androidsamples.koin.model.MyUserManager
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val allModules: List<Module> get() = listOf(appModule, userModule)

private val appModule: Module = applicationContext {
    factory {
        val obj = MyRandomModel()
        obj.data = "Modified MyRandomModel"
        obj
    }
    bean { MyAppManager("Awesome MyAppManager Injection") }
}

private val userModule: Module = applicationContext {
    factory { MyUserManager("MyUserManager", get()) }
}
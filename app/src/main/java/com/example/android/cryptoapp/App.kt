package com.example.android.cryptoapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.android.cryptoapp.di.component.DaggerAppComponent
import timber.log.Timber

//class App :Application() {
class App :MultiDexApplication() {



    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())


    }

    /*override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        MultiDex.install(this)
    }*/




}
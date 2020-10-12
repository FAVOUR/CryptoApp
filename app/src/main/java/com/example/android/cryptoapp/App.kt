package com.example.android.cryptoapp

import android.app.Application
import com.example.android.cryptoapp.di.component.DaggerAppComponent
import timber.log.Timber

class App :Application() {


    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())


    }


}
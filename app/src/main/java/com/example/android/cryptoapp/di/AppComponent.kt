package com.example.android.cryptoapp.di

import android.content.Context
import com.example.android.cryptoapp.activities.MainActivity
import com.example.android.cryptoapp.fragments.ConversionFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

     @Component.Factory
    interface  Factory {
         fun create(@BindsInstance context: Context):AppComponent
    }

     fun create(conversionFragment: ConversionFragment)

}


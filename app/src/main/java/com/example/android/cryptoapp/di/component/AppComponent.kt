package com.example.android.cryptoapp.di.component

import android.content.Context
import com.example.android.cryptoapp.di.module.ActivityModule
import com.example.android.cryptoapp.di.module.AppModule
import com.example.android.cryptoapp.ui.fragments.ConversionFragment
import com.example.android.cryptoapp.ui.fragments.EditorFragment
import com.example.android.cryptoapp.ui.fragments.ListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ActivityModule::class])
interface AppComponent {

     @Component.Factory
    interface  Factory {
         fun create(@BindsInstance context: Context): AppComponent
    }

     fun create(conversionFragment: ConversionFragment)
     fun create(conversionFragment: EditorFragment)
     fun create(conversionFragment: ListFragment)

}


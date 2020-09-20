package com.example.android.cryptoapp.di

import com.squareup.picasso.Picasso

 object AppModule {

    fun providePicassoInstance ():Picasso {
        return Picasso.get()
    }
}
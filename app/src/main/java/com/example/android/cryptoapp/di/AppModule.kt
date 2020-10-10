package com.example.android.cryptoapp.di

import com.example.android.cryptoapp.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {


    //Provide an instance of Picasso
    @Singleton //This indicates that it is tied to the livecycleScope
    @Provides
    fun providePicassoInstance ():Picasso {
        return Picasso.get()
    }

    //Provide an instance of Moshi
     @Singleton //This indicates that it is tied to the lifecycleScope
     @Provides
     fun provideMoshiInstance() :Moshi{
         return   Moshi.Builder()
                 .add(KotlinJsonAdapterFactory())
                 .build()
     }

    //Provide instance of LoggingInterceptor
    @Singleton
    @Provides
    fun provideHttpLogginInterceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().also {
                 it.level =HttpLoggingInterceptor.Level.BODY
        }
    }

    //Provide instance of okhttpclient
    @Singleton
    @Provides
    fun provideOkHttpClient(htttpInterceptor:HttpLoggingInterceptor):OkHttpClient{
           return OkHttpClient.Builder()
                   .addInterceptor(htttpInterceptor)
                   .build()

    }

    //Provide an instance of Retrofit
    @Singleton
    @Provides
    fun provideRetrofitInstance(moshi:Moshi,okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .baseUrl(Constants.CRYPTOCOMPARE_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()

    }




}
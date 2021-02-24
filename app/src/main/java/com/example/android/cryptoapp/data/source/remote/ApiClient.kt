package com.example.android.cryptoapp.data.source.remote

import com.example.android.cryptoapp.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Level.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by OZMA NIG COM LTD on 05-Oct-17.
 */
object ApiClient {

        val moshi = Moshi.Builder()
                                .add(KotlinJsonAdapterFactory())
                                .build()




        val okHttpclient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
                })
                .build()

        private const val CRYPTOCOMPARE_BASE_URL = "https://min-api.cryptocompare.com/data/"
        private var retrofit: Retrofit? = null
        val client: Retrofit?
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                                       .addConverterFactory(MoshiConverterFactory.create(moshi))
                                       .baseUrl(CRYPTOCOMPARE_BASE_URL)
                                       .client(okHttpclient)
                                       .build()
                }
                return retrofit
            }

//    }


    /*   Gson Converter Factory
    private const val CRYPTOCOMPARE_BASE_URL = "https://min-api.cryptocompare.com/data/"
    private var retrofit: Retrofit? = null
    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(CRYPTOCOMPARE_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(MainApiClient.okHttpclient)
                        .build()
            }
            return retrofit
        }
*/



}
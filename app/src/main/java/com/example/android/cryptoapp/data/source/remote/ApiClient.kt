package com.example.android.cryptoapp.data.source.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by OZMA NIG COM LTD on 05-Oct-17.
 */
object ApiClient {
    private const val CRYPTOCOMPARE_BASE_URL = "https://min-api.cryptocompare.com/data/"
    private var retrofit: Retrofit? = null
    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(CRYPTOCOMPARE_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }


    object MainApiClient {

        val moshi = Moshi.Builder()
                                .add(KotlinJsonAdapterFactory())
                                .build()
        private const val CRYPTOCOMPARE_BASE_URL = "https://min-api.cryptocompare.com/data/"
        private var retrofit: Retrofit? = null
        val client: Retrofit?
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                                       .addConverterFactory(MoshiConverterFactory.create(moshi))
                                       .baseUrl(CRYPTOCOMPARE_BASE_URL)
                                       .build()
                }
                return retrofit
            }

    }
}
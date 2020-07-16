package com.example.android.cryptoapp.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
}
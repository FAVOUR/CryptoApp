package com.example.android.cryptoapp.data.source.remote

import androidx.test.core.app.ApplicationProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GetCryptoRateTest {
  lateinit var retrofit: Retrofit
    @Before
    fun getClient() {
        val moshi =Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        retrofit =Retrofit.Builder()
                 .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
    }



    @After
    fun tearDown() {
    }

    fun startTrigger(){
        var inputStream = ApplicationProvider.getApplicationContext<>().
                
    }

    @Test
    fun checkMockSever (){
        var mockServer =MockWebServer()

//         mockServer.
    }

//    {"BTC":{"RUB":1089805.4},"ETH":{"RUB":31085.57}}
}
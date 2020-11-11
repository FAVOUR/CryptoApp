package com.example.android.cryptoapp.data.source.remote

import androidx.test.core.app.ApplicationProvider
import com.example.android.cryptoapp.util.MockResponseFileReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.InputStream

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



    @Test
    fun checkMockSever (){
        var mockServer =MockWebServer()

//         mockServer.
    }


    fun makeANetworkRequestAndCheckResult(){
        val mockServer = MockWebServer().enqueue(MockResponse().setBody(MockResponseFileReader("rates.json").content))

    }

}
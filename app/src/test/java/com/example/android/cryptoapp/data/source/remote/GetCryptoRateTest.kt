package com.example.android.cryptoapp.data.source.remote

import androidx.test.core.app.ApplicationProvider
import com.example.android.cryptoapp.util.MockResponseFileReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.HttpUrl
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.InputStream

class GetCryptoRateTest {
  lateinit var retrofit: Retrofit

     var mockServer = MockWebServer()
    @Before
    fun getClient() {

        mockServer.start(8080)

    mockServer.takeRequest()
        val baseUrl: HttpUrl = HttpUrl.get("https://ourapi.com/")


//        val moshi =Moshi.Builder()
//                .add(KotlinJsonAdapterFactory())
//                .build()
//        retrofit =Retrofit.Builder()
//                 .addConverterFactory(MoshiConverterFactory.create(moshi))
//                .build()
//
//
    }



    @After
    fun tearDown() {
        mockServer.shutdown()
    }


    @Test
    fun checkMockSever (){


//         mockServer.
    }

    @Test
    fun makeANetworkRequestAndCheckResult(){


//        mockServer.enqueue(MockResponse().setBody(MockResponseFileReader("rates.json").content))
        mockServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {

                 return  MockResponse().setStatus("200").setBody(MockResponseFileReader("rates.json").content)

            }
        }

    }

}
package com.example.android.cryptoapp.data.source.remote
import com.example.android.cryptoapp.CoroutineTestRule
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit

class GetCryptoRateTest {

     var mockServer = MockWebServer()
   lateinit var  cryptoRateService:CryptoCurrencyService
   lateinit var  moshi :Moshi

//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    @ExperimentalCoroutinesApi
    var testRule: CoroutineTestRule = CoroutineTestRule()



    @Before
    fun getClient() {

        mockServer.start(8080)

        val baseUrl: HttpUrl = MockWebServer().url("/")

        //Setups up moshi
               moshi =  Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
             //Sets up Okhttp client
         val client = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.SECONDS)
                .build()

        //Sets up retrofit service
        cryptoRateService= Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))

                .client(client)
                .build()
                .create(CryptoCurrencyService::class.java)

    }
    @After
    fun tearDown() {
        mockServer.shutdown()
    }


//    @Test
//    fun makeANetworkRequestAndCheckResult()= runBlocking {
//        val mockResponse =MockResponse().setStatus("200").setBody(getSuccessfulResponseFromFile("rates.json"))
//
//        mockServer.enqueue(mockResponse)
//
//            cryptoRateService.getACurrencyRate(CurrencyAbbreviation.NIGERIA_NAIRA.abbr)
//
//            val request = mockServer.takeRequest()
//
//            assertEquals("GET", `is`(request.method))
//        }




    fun  getSuccessfulResponseFromFile(path:String ):String {
        var inputStreamReader = InputStreamReader( this.javaClass.classLoader.getResourceAsStream(path))

        val content = inputStreamReader.readText()

        inputStreamReader.close()

        return content
    }

}
package com.example.android.cryptoapp.data.source.remote

import com.example.android.cryptoapp.currency_data.CurrencyAbbreviation
import com.example.android.cryptoapp.currency_data.JsonResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by OZMA NIG COM LTD on 05-Oct-17.
 */
interface CryptoCurrencyService {
    @GET("pricemulti?fsyms=BTC,ETH")
//    suspend fun getJsonResponse(@Query("tsyms") tsyms: String?): Call<JsonResponse?>?
     fun getJsonResponse(@Query("tsyms") tsyms: String?): Call<JsonResponse?>?

    @GET("pricemulti?fsyms=BTC,ETH")
    suspend fun getJsonResponse_(@Query("tsyms") tsyms: String?): Response<JsonResponse>
    
    @GET("pricemulti?fsyms=BTC,ETH")
    suspend fun getJsonResponses_(@Query("tsyms")  currencyAbbreviation : Array<out CurrencyAbbreviation?>): Response<List<JsonResponse?>>
}
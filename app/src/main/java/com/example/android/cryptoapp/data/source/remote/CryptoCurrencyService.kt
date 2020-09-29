package com.example.android.cryptoapp.data.source.remote

import com.example.android.cryptoapp.currency_data.JsonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by OZMA NIG COM LTD on 05-Oct-17.
 */
interface CryptoCurrencyService {
    @GET("pricemulti?fsyms=BTC,ETH")
    fun getJsonResponse(@Query("tsyms") tsyms: String?): Call<JsonResponse?>?
}
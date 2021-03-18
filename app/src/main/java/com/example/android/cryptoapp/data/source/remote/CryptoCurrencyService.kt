package com.example.android.cryptoapp.data.source.remote

import com.example.android.cryptoapp.data.model.CurrencyAbbreviation
import com.example.android.cryptoapp.data.model.CryptoRatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by OZMA NIG COM LTD on 05-Oct-17.
 */
interface CryptoCurrencyService {
    @GET("pricemulti?fsyms=BTC,ETH")
    suspend fun getACurrencyRate(@Query("tsyms") tsyms: String?): Response<CryptoRatesResponse>
    
    @GET("pricemulti?fsyms=BTC,ETH")
    suspend fun getSpecifiedCurrencyRate(@Query("tsyms")  currencyAbbreviation : Array<out CurrencyAbbreviation?>): Response<List<CryptoRatesResponse>>
}
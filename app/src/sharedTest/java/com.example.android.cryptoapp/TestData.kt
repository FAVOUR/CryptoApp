package com.example.android.cryptoapp

import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import okhttp3.mockwebserver.MockResponse
import okio.Okio
import java.io.InputStreamReader

object TestData {

     fun getCryptoCurrencyData() : CryptoCurrencyData {
         return CryptoCurrencyData(id=1,currencyName = "Nigeria", currencyAbbreviation = "NGN",currencySymbol = "",image =12345,btcRate = 3.65,ethRate = 234.56)
     }



}
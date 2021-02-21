package com.example.android.cryptoapp

import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData

object TestData {

     fun getCryptoCurrencyData() : CryptoCurrencyData {
         return CryptoCurrencyData(currencyName = "Nigeria", currencyAbbreviation = "NGN",currencySymbol = "",image =12345,btcRate = 3.65,ethRate = 234.56)
     }

}
package com.example.android.cryptoapp.data.source

import com.example.android.cryptoapp.data.model.CurrencyAbbreviation
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.util.Result

interface CommonCryptoRatesDataSource {

    suspend fun getSpecifiedCurrencyRates(currencyAbbreviation : Array<out CurrencyAbbreviation?>? =null): Result<List<CryptoCurrencyData>>

}
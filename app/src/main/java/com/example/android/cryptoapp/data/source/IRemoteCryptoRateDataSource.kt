package com.example.android.cryptoapp.data.source

import com.example.android.cryptoapp.data.model.CurrencyAbbreviation
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.util.Result

interface  IRemoteCryptoRateDataSource: CommonCryptoRatesDataSource {

    suspend fun refreshCryptoRates(cryptoCurrencyAbbreviation: CurrencyAbbreviation)
    suspend fun getACurrencyRate(currencyAbbreviation :CurrencyAbbreviation): Result<CryptoCurrencyData>


}
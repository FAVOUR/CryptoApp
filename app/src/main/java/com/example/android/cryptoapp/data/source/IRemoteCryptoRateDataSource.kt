package com.example.android.cryptoapp.data.source

import com.example.android.cryptoapp.currency_data.CurrencyAbbreviation
import com.example.android.cryptoapp.data.source.CommonCryptoRatesDataSource
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.util.Result

interface  IRemoteCryptoRateDataSource: CommonCryptoRatesDataSource {

    suspend fun refreshCryptoRates(cryptoCurrencyAbbreviation: CurrencyAbbreviation)
    suspend fun getCryptoRate(cryptoCurrencyAbbreviation: CurrencyAbbreviation): Result<List<CryptoCurrencyData>>


}
package com.example.android.cryptoapp.data.source

import com.example.android.cryptoapp.currency_data.CurrencyAbbreviation
import com.example.android.cryptoapp.data.source.CommonCryptoRatesDataSource

interface  IRemoteCryptoRateDataSource: CommonCryptoRatesDataSource {

    suspend fun refreshCryptoRates(cryptoCurrencyAbbreviation: CurrencyAbbreviation)

}
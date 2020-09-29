package com.example.android.cryptoapp.data.source

import com.example.android.cryptoapp.currency_data.CurrencyAbbreviation
import com.example.android.cryptoapp.domain.model.CryptoCurrencyRates
import com.example.android.cryptoapp.util.Result

interface CommonCryptoRatesDataSource {

    suspend fun getCryptoRates(): Result<List<CryptoCurrencyRates>>
    suspend fun getCryptoRate(cryptoCurrencyAbbreviation: CurrencyAbbreviation): Result<CryptoCurrencyRates>

}
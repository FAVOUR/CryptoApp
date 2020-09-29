package com.example.android.cryptoapp.data.source.remote

import com.example.android.cryptoapp.currency_data.CurrencyAbbreviation
import com.example.android.cryptoapp.data.source.IRemoteCryptoRateDataSource
import com.example.android.cryptoapp.data.source.local.db.CurrencyDao
import com.example.android.cryptoapp.domain.model.CryptoCurrencyRates
import com.example.android.cryptoapp.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class RemoteCryptoRateDataSource(private val cryptoRateDao: CurrencyDao, private val dispatcher: CoroutineDispatcher = Dispatchers.IO):IRemoteCryptoRateDataSource {
    override suspend fun refreshCryptoRates(cryptoCurrencyAbbreviation: CurrencyAbbreviation) {
return   }

    override suspend fun getCryptoRates(): Result<List<CryptoCurrencyRates>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCryptoRate(cryptoCurrencyAbbreviation: CurrencyAbbreviation): Result<CryptoCurrencyRates> {
        TODO("Not yet implemented")
    }
}
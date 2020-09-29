package com.example.android.cryptoapp.data.source.local

import androidx.lifecycle.LiveData
import com.example.android.cryptoapp.currency_data.CurrencyAbbreviation
import com.example.android.cryptoapp.data.source.ILocalCryptoRatesDataSource
import com.example.android.cryptoapp.domain.model.CryptoCurrencyRates
import com.example.android.cryptoapp.util.Result

class LocalCryptoRatesDataSource :ILocalCryptoRatesDataSource {
    override fun observeCryptoRates(): LiveData<Result<List<CryptoCurrencyRates>>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCryptoRate() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCryptoRate(rateId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllCurrencyRate() {
        TODO("Not yet implemented")
    }

    override suspend fun getCryptoRates(): Result<List<CryptoCurrencyRates>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCryptoRate(cryptoCurrencyAbbreviation: CurrencyAbbreviation): Result<CryptoCurrencyRates> {
        TODO("Not yet implemented")
    }
}
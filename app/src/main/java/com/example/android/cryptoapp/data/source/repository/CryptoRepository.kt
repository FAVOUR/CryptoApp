package com.example.android.cryptoapp.data.source.repository

import androidx.lifecycle.LiveData
import com.example.android.cryptoapp.currency_data.CurrencyAbbreviation
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.util.Result

interface CryptoRepository {

    fun observeCryptoRates(): LiveData<Result<List<CryptoCurrencyData>>>

    suspend fun getCryptoRateById(id: Int): Result<CryptoCurrencyData>

    suspend fun saveCryptoRates(cryptoRateData : List<CryptoCurrencyData>)

    suspend fun saveCryptoRate(cryptoRateData : CryptoCurrencyData):Long

    suspend fun refreshCryptoRates(cryptoCurrencyAbbreviation: CurrencyAbbreviation)

    suspend fun getCryptoRate(cryptoCurrencyAbbreviation: CurrencyAbbreviation):Result<CryptoCurrencyData>

    suspend fun deleteCryptoRate(cryptoCurrencyData: CryptoCurrencyData)

    suspend fun deleteAllCurrencyRate()
}
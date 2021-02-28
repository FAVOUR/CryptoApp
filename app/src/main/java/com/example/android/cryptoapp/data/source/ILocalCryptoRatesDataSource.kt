package com.example.android.cryptoapp.data.source

import androidx.lifecycle.LiveData
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.util.Result

interface ILocalCryptoRatesDataSource : CommonCryptoRatesDataSource {

    fun observeCryptoRates(): LiveData<Result<List<CryptoCurrencyData>>>

    suspend fun getCryptoRateById(id: Int): Result<CryptoCurrencyData>

    suspend fun getCryptoRate(): Result<List<CryptoCurrencyData>>

    suspend fun saveCryptoRates(cryptoRateData : List<CryptoCurrencyData>)

    suspend fun saveCryptoRate(cryptoRateData : CryptoCurrencyData):Long

    suspend fun deleteCryptoRate(cryptoCurrencyData: CryptoCurrencyData)

    suspend fun deleteAllCurrencyRate()


}
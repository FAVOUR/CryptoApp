package com.example.android.cryptoapp.data.source

import androidx.lifecycle.LiveData
import com.example.android.cryptoapp.data.source.CommonCryptoRatesDataSource
import com.example.android.cryptoapp.domain.model.CryptoCurrencyRates
import com.example.android.cryptoapp.util.Result

interface ILocalCryptoRatesDataSource : CommonCryptoRatesDataSource {

    fun observeCryptoRates(): LiveData<Result<List<CryptoCurrencyRates>>>

    suspend fun saveCryptoRate()

    suspend fun deleteCryptoRate(rateId:String)

    suspend fun deleteAllCurrencyRate()


}
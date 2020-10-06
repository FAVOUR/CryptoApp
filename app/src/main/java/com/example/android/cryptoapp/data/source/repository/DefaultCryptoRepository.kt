package com.example.android.cryptoapp.data.source.repository

import androidx.lifecycle.LiveData
import com.example.android.cryptoapp.currency_data.CurrencyAbbreviation
import com.example.android.cryptoapp.data.source.local.LocalCryptoRatesDataSource
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.data.source.remote.RemoteCryptoRateDataSource
import com.example.android.cryptoapp.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultCryptoRepository (val remoteCryptoRateDataSource: RemoteCryptoRateDataSource,
                               val localCryptoRatesDataSource: LocalCryptoRatesDataSource,
                               val dispatcher: CoroutineDispatcher =Dispatchers.IO):CryptoRepository{
    override fun observeCryptoRates(): LiveData<Result<List<CryptoCurrencyData>>> {
        return localCryptoRatesDataSource.observeCryptoRates()
    }

    override suspend fun getCryptoRateById(id: Int): Result<CryptoCurrencyData> {
       return localCryptoRatesDataSource.getCryptoRateById(id)

    }

    override suspend fun saveCryptoRates(cryptoRateData: List<CryptoCurrencyData>) {
            return localCryptoRatesDataSource.saveCryptoRates(cryptoRateData)
    }

    override suspend fun saveCryptoRate(cryptoRateData: CryptoCurrencyData) {
        return localCryptoRatesDataSource.saveCryptoRate(cryptoRateData)
    }

    //TODO Implement this later
    override suspend fun refreshCryptoRates(cryptoCurrencyAbbreviation: CurrencyAbbreviation) {

    }

//    override suspend fun getCryptoRate(cryptoCurrencyAbbreviation: CurrencyAbbreviation): Result<List<CryptoCurrencyData>> {
    override suspend fun getCryptoRate(cryptoCurrencyAbbreviation: CurrencyAbbreviation) {

//        remoteCryptoRateDataSource.getCryptoRate(cryptoCurrencyAbbreviation)

        fetchCryptoRateFromRemoteDataSource(cryptoCurrencyAbbreviation)
    }

/*
    override suspend fun getCryptoRates(cryptoCurrencyAbbreviation: CurrencyAbbreviation): Result<List<CryptoCurrencyData>> {
    }*/

    override suspend fun deleteCryptoRate(cryptoCurrencyData: CryptoCurrencyData) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllCurrencyRate() {
        TODO("Not yet implemented")
    }


//    private suspend fun UpdateCryptoRatesFromRemoteDateSource(){
//
//     val remoteDataSource = remoteCryptoRateDataSource.getCryptoRate()
//    }


    private suspend fun fetchCryptoRatesFromRemoteDataSource(isRefresh: Boolean,vararg currencyAbbreviation :CurrencyAbbreviation):Result<List<CryptoCurrencyData>>{
               val remoteDataSource = remoteCryptoRateDataSource.getCryptoRates(currencyAbbreviation)
               when (remoteDataSource) {
                   is Result.Success ->
                       saveCryptoRates(remoteDataSource.data)
                   is Result.Error -> {
                       throw Throwable(remoteDataSource.errorMessage)
                   }
               }
              return localCryptoRatesDataSource.getCryptoRates(currencyAbbreviation)


    }

    private suspend fun fetchCryptoRateFromRemoteDataSource( currencyAbbreviation :CurrencyAbbreviation){

            val remoteDataSource = remoteCryptoRateDataSource.getCryptoRate(currencyAbbreviation)
            when (remoteDataSource) {
                is Result.Success ->
                    saveCryptoRate(remoteDataSource.data)
                is Result.Error -> {
                    throw Throwable(remoteDataSource.errorMessage)
                }
            }



    }






}
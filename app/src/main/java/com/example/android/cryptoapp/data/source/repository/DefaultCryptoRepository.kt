package com.example.android.cryptoapp.data.source.repository

import androidx.lifecycle.LiveData
import com.example.android.cryptoapp.data.model.CurrencyAbbreviation
import com.example.android.cryptoapp.data.source.local.LocalCryptoRatesDataSource
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.data.source.remote.RemoteCryptoRateDataSource
import com.example.android.cryptoapp.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultCryptoRepository @Inject constructor(val remoteCryptoRateDataSource: RemoteCryptoRateDataSource,
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

    override suspend fun saveCryptoRate(cryptoRateData: CryptoCurrencyData):Long {
        return localCryptoRatesDataSource.saveCryptoRate(cryptoRateData)
    }

    //TODO Implement this later
    override suspend fun refreshCryptoRates(cryptoCurrencyAbbreviation: CurrencyAbbreviation) {

    }

    override suspend fun getCryptoRate(cryptoCurrencyAbbreviation: CurrencyAbbreviation):Result<CryptoCurrencyData> = withContext(dispatcher) {

//        remoteCryptoRateDataSource.getCryptoRate(cryptoCurrencyAbbreviation)

        return@withContext remotelyFetchCryptoRates(cryptoCurrencyAbbreviation)
    }

/*
    override suspend fun getCryptoRates(cryptoCurrencyAbbreviation: CurrencyAbbreviation): Result<List<CryptoCurrencyData>> {
    }*/

    override suspend fun deleteCryptoRate(cryptoCurrencyData: CryptoCurrencyData) {
    }

    override suspend fun deleteAllCurrencyRate() {
    }



    private suspend fun remotelyFetchListOfCryptoRates(isRefresh: Boolean,vararg currencyAbbreviation :CurrencyAbbreviation):Result<List<CryptoCurrencyData>> = withContext(dispatcher) {
               val remoteDataSource = remoteCryptoRateDataSource.getSpecifiedCurrencyRates(currencyAbbreviation)
               when (remoteDataSource) {
                   is Result.Success ->
                       //Convert to db model and save
                       saveCryptoRates(remoteDataSource.data)
                   is Result.Error -> {
                       throw Throwable(remoteDataSource.errorMessage)
                   }
               }
              return@withContext localCryptoRatesDataSource.getSpecifiedCurrencyRates(currencyAbbreviation)


    }

    private suspend fun remotelyFetchCryptoRates(currencyAbbreviation :CurrencyAbbreviation):Result<CryptoCurrencyData>{

            val remoteDataSource = remoteCryptoRateDataSource.getACurrencyRate(currencyAbbreviation)
            val value:Long =0L

         return when (remoteDataSource) {
                is Result.Success -> //Could be  successDOT and the one that finally gets to the viewmodel is now just SUCCESS
                    returnRateSaved{  saveCryptoRate(remoteDataSource.data) }  //Todo Re-evaluate  this approach later
//                        withContext(dispatcher){
//                         val resp =  saveCryptoRate(remoteDataSource.data)
//
//                            localCryptoRatesDataSource.getCryptoRate()
//                        }

//                 localCryptoRatesDataSource.getCryptoRateById(id)

                 is Result.Error -> {
                     Result.Error(remoteDataSource.errorMessage)
//                    throw Throwable(remoteDataSource.errorMessage)   //TODO Find out if it is reasonable to have a domain sealed class as well as that for  Datatransfer
                 }
                 is Result.Loading ->{
                     Result.Loading
                 }

         }


    }

    suspend fun returnRateSaved( id : suspend () -> Long ):Result<CryptoCurrencyData>{  //Todo Re - evaluate You can use result<Long>
        val id =id.invoke().toInt()
        return  localCryptoRatesDataSource.getCryptoRateById(id)

    }






}
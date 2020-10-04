package com.example.android.cryptoapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.android.cryptoapp.currency_data.CurrencyAbbreviation
import com.example.android.cryptoapp.data.source.ILocalCryptoRatesDataSource
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.data.source.local.db.CurrencyDao
import com.example.android.cryptoapp.domain.model.CryptoCurrencyRates
import com.example.android.cryptoapp.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class LocalCryptoRatesDataSource (private val cryptoRateDao: CurrencyDao,  private val dispatcher: CoroutineDispatcher = Dispatchers.IO):ILocalCryptoRatesDataSource {
    override fun observeCryptoRates(): LiveData<Result<List<CryptoCurrencyData>>> {

    return cryptoRateDao.ObserveCryptoRates().map{
         Result.Success(it)
    }

    }

    override suspend fun saveCryptoRate(cryptoRateData :List<CryptoCurrencyData>) {
         cryptoRateDao.saveCryptoCurrencyRates(cryptoRateData)
    }

    override suspend fun saveCryptoRate(cryptoRateData: CryptoCurrencyData) {
        cryptoRateDao.saveCryptoCurrencyRate(cryptoRateData)
    }

    override suspend fun deleteCryptoRate(cryptoCurrencyData: CryptoCurrencyData) {

       cryptoRateDao.deleteCryptoRate(cryptoCurrencyData)
    }

    override suspend fun deleteAllCurrencyRate() {

        cryptoRateDao.deleteAllCryptoRates()
    }

    override suspend fun getCryptoRates(cryptoCurrencyAbbreviation: String): Result<List<CryptoCurrencyData>> {

     val result = cryptoRateDao.getLocalCryptoRates()

       return try {
              Result.Loading
              if(result != null){
                  Result.Success(result)
              } else{
                  Result.Error("Unable to retrieve Crypto Rates ")
              }
        }catch (e:Throwable){
           Result.Error("Error retrieving Records ")

       }


    }

    override suspend fun getCryptoRateById(id: Int): Result<List<CryptoCurrencyData>> {
       val result=  cryptoRateDao.getCryptoRateById(id)


        return try {
            Result.Loading
            if(result != null){
                Result.Success(result)
            } else{
                Result.Error("Unable to retrieve Crypto Rates ")
            }
        }catch (e:Throwable){
            Result.Error("Error retrieving Records ")

        }
    }


}
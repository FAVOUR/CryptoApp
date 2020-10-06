package com.example.android.cryptoapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.android.cryptoapp.currency_data.CurrencyAbbreviation
import com.example.android.cryptoapp.data.source.ILocalCryptoRatesDataSource
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.data.source.local.db.CurrencyDao
import com.example.android.cryptoapp.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalCryptoRatesDataSource (private val cryptoRateDao: CurrencyDao,  private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):ILocalCryptoRatesDataSource {
    override fun observeCryptoRates(): LiveData<Result<List<CryptoCurrencyData>>> {

    return cryptoRateDao.ObserveCryptoRates().map {
        Result.Success(it)
    }
      }

    override suspend fun saveCryptoRates(cryptoRateData :List<CryptoCurrencyData>) = withContext(ioDispatcher)  {
         cryptoRateDao.saveCryptoCurrencyRates(cryptoRateData)
    }

    override suspend fun saveCryptoRate(cryptoRateData: CryptoCurrencyData) = withContext(ioDispatcher)  {
        cryptoRateDao.saveCryptoCurrencyRate(cryptoRateData)
    }

    override suspend fun deleteCryptoRate(cryptoCurrencyData: CryptoCurrencyData)= withContext(ioDispatcher)  {

       cryptoRateDao.deleteCryptoRate(cryptoCurrencyData)
    }

    override suspend fun deleteAllCurrencyRate()  = withContext(ioDispatcher) {

        cryptoRateDao.deleteAllCryptoRates()
    }

    override suspend fun getCryptoRates( currencyAbbreviation : Array<out CurrencyAbbreviation?>?): Result<List<CryptoCurrencyData>> = withContext(ioDispatcher) {

     val result = cryptoRateDao.getLocalCryptoRates()

       return@withContext try {
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

    override suspend fun getCryptoRateById(id: Int): Result<CryptoCurrencyData> = withContext(ioDispatcher) {
       val result=  cryptoRateDao.getCryptoRateById(id)


        return@withContext try {
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
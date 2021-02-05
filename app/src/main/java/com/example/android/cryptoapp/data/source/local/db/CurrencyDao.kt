package com.example.android.cryptoapp.data.source.local.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CurrencyDao {

    @Query("Select * from CryptoCurrencyData")
   suspend fun  getLocalCryptoRates(): List<CryptoCurrencyData>?

    @Query("Select * from CryptoCurrencyData")
     fun ObserveCryptoRates():LiveData<List<CryptoCurrencyData>>

    @Query("Select * from CryptoCurrencyData")
     fun ObserveCryptoRate():LiveData<List<CryptoCurrencyData>>

    @Query("Select * from CryptoCurrencyData where id = :rateId")
    fun getCryptoRateById(rateId:Int):CryptoCurrencyData

    @Query("Delete from CryptoCurrencyData ")
    fun deleteAllCryptoRates()

    @Delete
    fun deleteCryptoRate(cryptoCurrencyData: CryptoCurrencyData)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert
    suspend  fun saveCryptoCurrencyRate(cryptoCurrencyData: CryptoCurrencyData):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun saveCryptoCurrencyRates(cryptoCurrencyData: List<CryptoCurrencyData>)
}
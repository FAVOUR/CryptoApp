package com.example.android.cryptoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrencyDao {

    @Query("Select * from CryptoCurrencyData")
   suspend fun  getSavedCurrencies(): List<CryptoCurrencyData>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun saveCurrency(cryptoCurrencyData: CryptoCurrencyData)
}
package com.example.android.cryptoapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CryptoCurrencyData::class],version = 1,exportSchema = false)
abstract class CurrencyRoomDatabase : RoomDatabase() {

    abstract fun currencyDao() : CurrencyDao

      companion object{

          @Volatile
          private var  INSTANCE:CurrencyRoomDatabase?= null



          fun getDataBase(context: Context) :CurrencyRoomDatabase {

              val tempInstance: CurrencyRoomDatabase? = INSTANCE

              if (tempInstance != null) {
                  return tempInstance
              }

              synchronized (this){
              val currencyDatabaseInstance = Room.databaseBuilder(context, CurrencyRoomDatabase::class.java, "currency_db")
                      .build()

              INSTANCE = currencyDatabaseInstance

              return currencyDatabaseInstance

              }
          }
      }


}
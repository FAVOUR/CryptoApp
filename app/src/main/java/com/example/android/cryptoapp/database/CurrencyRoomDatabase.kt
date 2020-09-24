package com.example.android.cryptoapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

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
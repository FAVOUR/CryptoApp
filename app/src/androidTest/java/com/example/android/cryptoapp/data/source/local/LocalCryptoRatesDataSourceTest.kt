package com.example.android.cryptoapp.data.source.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.data.source.local.db.CurrencyDao
import com.example.android.cryptoapp.data.source.local.db.CurrencyRoomDatabase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalCryptoRatesDataSourceTest {

    lateinit var  db :CurrencyRoomDatabase
    lateinit var  context:Context
    lateinit var  dao:CurrencyDao
//    lateinit var

    @get:Rule
    val task :Instant =InstantTask

    @Before
    fun setUp() {
        context =ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context,CurrencyRoomDatabase::class.java)
                .allowMainThreadQueries()
                .build()
       dao= db.currencyDao()

    }

    @After
    fun tearDown() {

        db.close()
    }

    @Test
    fun `create and save Rate return rates`() = runBlocking{
        val rate = CryptoCurrencyData(currencyName = "Nigeria", currencyAbbreviation = "NGN",currencySymbol = "",image =12345,btcRate = 3.65,ethRate = 234.56)

         dao.saveCryptoCurrencyRate(rate)

        val savedData= dao.getLocalCryptoRates()?.get(0)

         assertThat(rate).isEqualTo(savedData)

    }
}
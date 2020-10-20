package com.example.android.cryptoapp.data.source.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before

import org.junit.Assert.*


class LocalCryptoRatesDataSourceTest {

    lateinit var  db :Room
    lateinit var  context:Context
//    lateinit var


    @Before
    fun setUp() {
        context =ApplicationProvider.getApplicationContext()
//        db = Room.inMemoryDatabaseBuilder(context)

    }

    @After
    fun tearDown() {
    }
}
/*
package com.example.android.cryptoapp.viewmodel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.data.source.repository.DefaultCryptoRepository
import com.example.android.cryptoapp.util.getOrAwaitNextValue
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.apache.maven.model.Repository
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class ListViewModelTest{
    val repository = mock(DefaultCryptoRepository::class.java)
    lateinit var listViewModel:ListViewModel

//    @get:Rule
//    val executor = InstantTaskExecutorRule()

    @Before
    fun setup(){
       listViewModel= ListViewModel(repository = repository)
    }

    @After
    fun tearDown(){

    }

//    @Test
  */
/*  fun  getResult_filterResponse() = runBlocking{
    val cryptoData =CryptoCurrencyData(currencyName = "Nigeria", currencyAbbreviation = "NGN",currencySymbol = "",image =12345,btcRate = 3.65,ethRate = 234.56)

        listViewModel.addNewData()

        val response = listViewModel._cryptoCurrencyData
//                .getOrAwaitNextValue()


//         print("response " + Gson().toJson(response))
         print("response " + Gson().toJson(response.value))
//        Log.e("response",Gson().toJson(response))

        assertThat(response.value).isNotEmpty()

    }*//*


}*/

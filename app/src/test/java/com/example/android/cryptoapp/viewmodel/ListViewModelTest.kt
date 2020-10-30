package com.example.android.cryptoapp.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.cryptoapp.data.source.repository.DefaultCryptoRepository
import org.apache.maven.model.Repository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class ListViewModelTest{
    val repository = mock(DefaultCryptoRepository::class.java)
    lateinit var listViewModel:ListViewModel
    @Before
    fun setup(){
       listViewModel= ListViewModel(repository = repository)
    }

    @After
    fun tearDown(){

    }

    @Test
    fun  getResult_filterResponse(){



    }

}
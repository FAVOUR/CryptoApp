package com.example.android.cryptoapp.viewmodel

import android.net.UrlQuerySanitizer
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.cryptoapp.domain.model.CryptoCurrencyRates
import com.example.android.cryptoapp.adapter.RatesAdapter
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.data.source.repository.CryptoRepository
import com.example.android.cryptoapp.util.Result
import java.lang.Error
import javax.inject.Inject

class ListViewModel @Inject constructor(val repository: CryptoRepository ):ViewModel() {


    var resultAdapter: RatesAdapter? = null
    var layoutManager: LinearLayoutManager? = null
    lateinit var results: MutableList<CryptoCurrencyRates>

    var soFar: List<CryptoCurrencyRates>? = null
    lateinit var output: CryptoCurrencyRates
    var image = 0
    lateinit var currencyAbr: String
    lateinit var currencySymbol: String
    lateinit var currencyName: String
    var check :Boolean = true
//    var btcRate by Delegates.notNull<Double>()
//    var ethRate by  Delegates.notNull<Double>()
//    var cryptoClient: CryptoCurrencyService? = null
//    var btcConversionRates: Btc? = null
//    var ethConversionRates: Eth? = null
//    var bundle: Bundle? = null
    var _cryptoCurrencyData =MutableLiveData <List<CryptoCurrencyData>>()
    val  cryptoCurrencyData :LiveData<List<CryptoCurrencyData>>
            get () =repository.observeCryptoRates().switchMap {
//                 getResult(it)
                _cryptoCurrencyData.value = getActualDataFromRepositoryData(it)
                _cryptoCurrencyData
     }
//     get() = _cryptoCurrencyData


   private fun <T> getActualDataFromRepositoryData(result:Result<T>): T {

     return  when(result){
           is Result.Success<T> ->{
               result.data
           }
            else ->throw NoSuchElementException("Expected data wrapped with  ${Result.Success::class.java} class but got $result ")
       }
   }

    private fun getResult(cryptoRates:Result<List<CryptoCurrencyData>>):LiveData<List<CryptoCurrencyData>>{

    if(cryptoRates is Result.Success){
        //Todo
        //Add loadingDataError mutable live data false
//        viewModelScope.launch {
            _cryptoCurrencyData.value =cryptoRates.data

//        }
    }else{
        _cryptoCurrencyData.value = emptyList()
          //Todo
          //Add Snackbar(showSnackbarMessage) using events
         //Add loadingDataError mutable live data true
    }
         return _cryptoCurrencyData
    }



/*     //Gets the list of Data in the database
    fun getCurrencies()= viewModelScope.launch {

//         _cryptoCurrencyData.value =  currencyDao.getLocalCryptoRates()
         repository.observeCryptoRates()
    }*/



}
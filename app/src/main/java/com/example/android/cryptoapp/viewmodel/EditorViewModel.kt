package com.example.android.cryptoapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.android.cryptoapp.currency_data.*
import com.example.android.cryptoapp.data.source.repository.CryptoRepository
import com.example.android.cryptoapp.util.Result
import com.google.gson.Gson
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

class EditorViewModel @Inject constructor(val repository: CryptoRepository):ViewModel() {

     var currencyAbr: CurrencyAbbreviation?=null
    lateinit var currencySymbol: CurrencySymbol
     var _currencyName: String?=null
     var image : Int by Delegates.notNull<Int>()
    lateinit var cryptoRatesResponse: CryptoRatesResponse

    private var _isLoading=MutableLiveData<Boolean>()
     val isLoading:LiveData<Boolean>
       get() = _isLoading
    private var _errorMessage=MutableLiveData<String>()
     val errorMessage:LiveData<String>
       get() = _errorMessage

    lateinit var counties :Array<String>
     var selectedcountry =MutableLiveData<String>()

    fun setCountries(allCounties:Array<String>){
        counties=allCounties
    }

        fun  getCryptoRate() = viewModelScope.launch {
//          Log.e("_currencyName>>>> ",_currencyName)
//          Log.e("_selectedcountry>>>> ",selectedcountry.value)
          Log.e("_selectedcountry>>>> ",Gson().toJson(currencyAbr))
            _isLoading.value=true

            var response=   repository.getCryptoRate(currencyAbr!!)


            when (response) {
                is Result.Success ->{
                    Timber.e(Gson().toJson(response.data))
                    _isLoading.value=false
                }
                is Result.Error -> {
                    _isLoading.value=false
                    _errorMessage.value=response.errorMessage
                }
                is Result.Loading ->{
                    _isLoading.value=true

                }

            }


        }

}
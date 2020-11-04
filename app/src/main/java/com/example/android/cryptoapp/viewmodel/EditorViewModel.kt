package com.example.android.cryptoapp.viewmodel

import android.util.Log
import android.widget.Spinner
import androidx.lifecycle.*
import com.example.android.cryptoapp.currency_data.*
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.data.source.local.db.CurrencyDao
import com.example.android.cryptoapp.data.source.remote.CryptoCurrencyService
import com.example.android.cryptoapp.data.source.repository.CryptoRepository
import com.example.android.cryptoapp.util.Result
import com.google.gson.Gson
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

class EditorViewModel @Inject constructor(val repository: CryptoRepository):ViewModel() {
//    var currencySpinner: Spinner? = null
//    var cryptoClient: CryptoCurrencyService? = null
//    var conversionFromBtc: Double by Delegates.notNull<Double>()
//    var conversionFromEth: Double by Delegates.notNull<Double>()
    lateinit var currencyAbr: CurrencyAbbreviation
    lateinit var currencySymbol: CurrencySymbol
    lateinit var _currencyName: String
     var image : Int by Delegates.notNull<Int>()
    lateinit var jsonResponse: JsonResponse

    private var _isLoading=MutableLiveData<Boolean>()
     val isLoading:LiveData<Boolean>
       get() = _isLoading
    private var _errorMessage=MutableLiveData<String>()
     val errorMessage:LiveData<String>
       get() = _errorMessage


/*
       fun saveData() = viewModelScope.launch{

//           currencyDao.saveCurrency(CryptoCurrencyData(currencySymbol=currencySymbol.name, currencyName = _currencyName,currencyAbbreviation = currencyAbr.name,
//                                                        btcRate = conversionFromBtc, ethRate = conversionFromEth,image = image))

               repository.saveCryptoRate(cryptoRateData = CryptoCurrencyData(currencySymbol=currencySymbol.name, currencyName = _currencyName,currencyAbbreviation = currencyAbr.name,
                                                        btcRate = conversionFromBtc, ethRate = conversionFromEth,image = image))
       }*/



        fun  getCryptoRate() = viewModelScope.launch {
//                liveData<Result<Unit>> {
            /*  var rates =  kotlin.runCatching { //Serves same function as try catch
                repository.getCryptoRate(currencyAbr)
            }
                  rates  .onFailure {

               Log.e("yeah " , Gson().toJson(it))
           }*/  //TODO If you consider this approach then reconsider the need for having loading as part of the sealed class
//        }

            _isLoading.value=true

            var response=   repository.getCryptoRate(currencyAbr)


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
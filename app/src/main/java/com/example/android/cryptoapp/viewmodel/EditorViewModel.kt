package com.example.android.cryptoapp.viewmodel

import android.widget.Spinner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.cryptoapp.currency_data.Btc
import com.example.android.cryptoapp.currency_data.Eth
import com.example.android.cryptoapp.currency_data.JsonResponse
import com.example.android.cryptoapp.database.CryptoCurrencyData
import com.example.android.cryptoapp.database.CurrencyDao
import com.example.android.cryptoapp.database.CurrencyRoomDatabase
import com.example.android.cryptoapp.rest.CryptoCurrencyService
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class EditorViewModel(val currencyDao: CurrencyDao):ViewModel() {
    var currencySpinner: Spinner? = null
    var cryptoClient: CryptoCurrencyService? = null
      var btcConversionRates: Btc?=null
      var ethConversionRates: Eth?= null
    var conversionFromBtc: Double by Delegates.notNull<Double>()
    var conversionFromEth: Double by Delegates.notNull<Double>()
    lateinit var currencyAbr: String
    lateinit var currencySymbol: String
    lateinit var _currencyName: String
     var image : Int by Delegates.notNull<Int>()
    lateinit var jsonResponse: JsonResponse


       fun saveData() = viewModelScope.launch{

           currencyDao.saveCurrency(CryptoCurrencyData(currencySymbol=currencySymbol, currencyName = _currencyName,currencyAbbreviation = currencyAbr,
                                                        btcRate = conversionFromBtc, ethRate = conversionFromEth,image = image))
       }





}
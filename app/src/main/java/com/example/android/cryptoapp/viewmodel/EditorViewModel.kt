package com.example.android.cryptoapp.viewmodel

import android.widget.Spinner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.cryptoapp.currency_data.*
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import com.example.android.cryptoapp.data.source.local.db.CurrencyDao
import com.example.android.cryptoapp.data.source.remote.CryptoCurrencyService
import com.example.android.cryptoapp.data.source.repository.CryptoRepository
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class EditorViewModel(val currencyDao: CurrencyDao,val repository: CryptoRepository):ViewModel() {
    var currencySpinner: Spinner? = null
    var cryptoClient: CryptoCurrencyService? = null
      var btcConversionRates: Btc?=null
      var ethConversionRates: Eth?= null
    var conversionFromBtc: Double by Delegates.notNull<Double>()
    var conversionFromEth: Double by Delegates.notNull<Double>()
    lateinit var currencyAbr: CurrencyAbbreviation
    lateinit var currencySymbol: CurrencySymbol
    lateinit var _currencyName: String
     var image : Int by Delegates.notNull<Int>()
    lateinit var jsonResponse: JsonResponse


       fun saveData() = viewModelScope.launch{

//           currencyDao.saveCurrency(CryptoCurrencyData(currencySymbol=currencySymbol.name, currencyName = _currencyName,currencyAbbreviation = currencyAbr.name,
//                                                        btcRate = conversionFromBtc, ethRate = conversionFromEth,image = image))

               repository.saveCryptoRate(cryptoRateData = CryptoCurrencyData(currencySymbol=currencySymbol.name, currencyName = _currencyName,currencyAbbreviation = currencyAbr.name,
                                                        btcRate = conversionFromBtc, ethRate = conversionFromEth,image = image))
       }





}
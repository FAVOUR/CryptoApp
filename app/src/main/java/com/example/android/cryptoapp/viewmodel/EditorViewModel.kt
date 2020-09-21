package com.example.android.cryptoapp.viewmodel

import android.widget.Spinner
import androidx.lifecycle.ViewModel
import com.example.android.cryptoapp.currency_data.Btc
import com.example.android.cryptoapp.currency_data.Eth
import com.example.android.cryptoapp.currency_data.JsonResponse
import com.example.android.cryptoapp.rest.CryptoCurrencyService
import kotlin.properties.Delegates

class EditorViewModel :ViewModel() {
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



}
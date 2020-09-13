package com.example.android.cryptoapp.viewmodel

import android.widget.Spinner
import androidx.lifecycle.ViewModel
import com.example.android.cryptoapp.currency_data.Btc
import com.example.android.cryptoapp.currency_data.Eth
import com.example.android.cryptoapp.currency_data.JsonResponse
import com.example.android.cryptoapp.rest.CryptoCurrencyService

class EditorViewModel :ViewModel() {
    var currencySpinner: Spinner? = null
    var cryptoClient: CryptoCurrencyService? = null
    var btcConversionRates: Btc? = null
    var ethConversionRates: Eth? = null
    var conversionFromBtc: Double? = null
    var conversionFromEth: Double? = null
    var currencyAbr: String? = null
    var currencySymbol: String? = null
    var _currencyName: String? = null
    var image = 0
    var jsonResponse: JsonResponse? = null



}
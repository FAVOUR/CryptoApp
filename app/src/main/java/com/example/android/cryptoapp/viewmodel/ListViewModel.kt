package com.example.android.cryptoapp.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.cryptoapp.Results
import com.example.android.cryptoapp.adapter.RatesAdapter
import com.example.android.cryptoapp.currency_data.Btc
import com.example.android.cryptoapp.currency_data.Eth
import com.example.android.cryptoapp.rest.CryptoCurrencyService
import kotlin.properties.Delegates

class ListViewModel:ViewModel() {


    var resultAdapter: RatesAdapter? = null
    var layoutManager: LinearLayoutManager? = null
    lateinit var results: MutableList<Results>

    var soFar: List<Results>? = null
    lateinit var output: Results
    var image = 0
    lateinit var currencyAbr: String
    lateinit var currencySymbol: String
    lateinit var currencyName: String
    var check :Boolean = true
    var btcRate by Delegates.notNull<Double>()
    var ethRate by  Delegates.notNull<Double>()
    var cryptoClient: CryptoCurrencyService? = null
    var btcConversionRates: Btc? = null
    var ethConversionRates: Eth? = null
    var bundle: Bundle? = null
}
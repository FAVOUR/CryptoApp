package com.example.android.cryptoapp.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat
import kotlin.properties.Delegates

class ConversionViewmodel:ViewModel() {
    var image = 0
    var check = false
    lateinit var currencyAbr: String
    lateinit var currencySymbol: String
    lateinit var currencyName: String
    var btcRate by Delegates.notNull<Double>()
    var ethRate by Delegates.notNull<Double>()
    lateinit var format: DecimalFormat
    lateinit var bundle: Bundle
}
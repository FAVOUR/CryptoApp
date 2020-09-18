package com.example.android.cryptoapp.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class ConversionViewmodel:ViewModel() {
    var image = 0
    var check = false
    var currencyAbr: String? = null
    var currencySymbol: String? = null
    var currencyName: String? = null
    var btcRate: Double? = null
    var ethRate: Double? = null
    var format: DecimalFormat? = null
    var bundle: Bundle? = null
}
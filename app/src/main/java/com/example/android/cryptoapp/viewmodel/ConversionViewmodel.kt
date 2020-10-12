package com.example.android.cryptoapp.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.android.cryptoapp.data.source.local.db.CurrencyDao
import com.example.android.cryptoapp.data.source.repository.CryptoRepository
import java.text.DecimalFormat
import javax.inject.Inject
import kotlin.properties.Delegates

class ConversionViewmodel @Inject constructor(val currencyDao: CurrencyDao, val repository: CryptoRepository):ViewModel() {

    var image = 0
    lateinit var currencyAbr: String
    lateinit var currencySymbol: String
    lateinit var currencyName: String
    var btcRate by Delegates.notNull<Double>()
    var ethRate by Delegates.notNull<Double>()
     var format: DecimalFormat = DecimalFormat()
     var bundle: Bundle? = null

    var check =  bundle == null

}
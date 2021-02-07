package com.example.android.cryptoapp.domain.model

import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData


//TODO
 /*  1)change the parameter name of first and second rate
   2) Rename the class probably currency crypto */
data class CryptoCurrencyRates(var  id:Long, val image: Int, val firstExRate: Double, val secondExRate: Double, val name: String, val abbrivation: String, val symbol: String){


}
    fun  List<CryptoCurrencyData>.asUIModel():List<CryptoCurrencyRates>{

        return map {
            CryptoCurrencyRates(id = it.id,image = it.image,firstExRate =  it.btcRate,secondExRate =  it.ethRate, name = it.currencyName, abbrivation = it.currencyAbbreviation, symbol = it.currencySymbol)
        }

    }

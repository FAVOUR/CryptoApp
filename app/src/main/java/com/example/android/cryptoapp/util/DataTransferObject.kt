package com.example.android.cryptoapp.util

import com.example.android.cryptoapp.currency_data.CurrencyAbbreviation
import com.example.android.cryptoapp.currency_data.JsonResponse
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData


fun List<JsonResponse?>?.asDataBaseModel(abbreviation: CurrencyAbbreviation):  List<CryptoCurrencyData>{


    var cryptoRatesList:MutableList<CryptoCurrencyData> =ArrayList()



    for(rates in this!! ){
    val currency = CryptoCurrencyData(currencySymbol= getSymbol(abbreviation).name, currencyName = getCurrencyName(abbreviation).name,currencyAbbreviation = abbreviation.name,
             btcRate =  getBtcRate(abbreviation,rates?.bTC), ethRate = getEthRate(abbreviation,rates?.eTH),image = getCurrencyImage(abbreviation))
        cryptoRatesList.add(currency)
    }

    return cryptoRatesList

}

fun JsonResponse?.asDataBaseModel(abbreviation: CurrencyAbbreviation):  CryptoCurrencyData{

    val cryptoRate = CryptoCurrencyData(currencySymbol= getSymbol(abbreviation).symbol, currencyName = getCurrencyName(abbreviation).currencyName,currencyAbbreviation = abbreviation.abbr,
                btcRate =  getBtcRate(abbreviation,this?.bTC), ethRate = getEthRate(abbreviation,this?.eTH),image = getCurrencyImage(abbreviation))


    return cryptoRate

}


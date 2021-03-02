package com.example.android.cryptoapp.util

import com.example.android.cryptoapp.data.model.CurrencyAbbreviation
import com.example.android.cryptoapp.data.model.CryptoRatesResponse
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData


fun List<CryptoRatesResponse?>?.asDataBaseModel(abbreviation: CurrencyAbbreviation):  List<CryptoCurrencyData>{


    var cryptoRatesList:MutableList<CryptoCurrencyData> =ArrayList()



    for(rates in this!! ){
    val currency = CryptoCurrencyData(currencySymbol= getSymbol(abbreviation).name, currencyName = getCurrencyName(abbreviation).name,currencyAbbreviation = abbreviation.name,
             btcRate =  getBtcRate(abbreviation,rates?.bTC), ethRate = getEthRate(abbreviation,rates?.eTH),image = getCurrencyImage(abbreviation))
        cryptoRatesList.add(currency)
    }

    return cryptoRatesList

}

fun CryptoRatesResponse?.asDataBaseModel(abbreviation: CurrencyAbbreviation):  CryptoCurrencyData{

    val cryptoRate = CryptoCurrencyData(currencySymbol= getSymbol(abbreviation).symbol, currencyName = getCurrencyName(abbreviation).currencyName,currencyAbbreviation = abbreviation.abbr,
                btcRate =  getBtcRate(abbreviation,this?.bTC), ethRate = getEthRate(abbreviation,this?.eTH),image = getCurrencyImage(abbreviation))


    return cryptoRate

}

  //With this I am rest Assured any dataType can bear "asdatabasemodel" as done with the list and just DTO object suffix
  fun CryptoCurrencyData.asDataBaseModel(){

  }


  //  OR better still you can make T be an interface that must be implemented by all DTO
   interface DataTransferObject<T> {
      fun T.asDataBaseModel()
      fun List<T>.asDataBaseModel()

  }



package com.example.android.cryptoapp.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.android.cryptoapp.data.source.local.db.CryptoCurrencyData
import kotlinx.android.parcel.Parcelize


//TODO
 /*  1)change the parameter name of first and second rate
     2) Rename the class probably currency crypto */
@Parcelize
data class CryptoCurrencyRates(var  id:Long, val image: Int, val firstExRate: Double, val secondExRate: Double, val name: String, val abbrivation: String, val symbol: String):Parcelable


    fun  List<CryptoCurrencyData>.asUIModel():List<CryptoCurrencyRates>{

        return map {
            CryptoCurrencyRates(id = it.id,image = it.image,firstExRate =  it.btcRate,secondExRate =  it.ethRate, name = it.currencyName, abbrivation = it.currencyAbbreviation, symbol = it.currencySymbol)
        }

    }


    fun  CryptoCurrencyRates.asDBModel():CryptoCurrencyData{

    return CryptoCurrencyData(id = id,image = image,btcRate =  firstExRate,ethRate =  secondExRate, currencyName = name, currencyAbbreviation = abbrivation, currencySymbol = symbol)


}

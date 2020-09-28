package com.example.android.cryptoapp.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CryptoCurrencyData (

    @PrimaryKey(autoGenerate = true)
    var  id:Int=0,
    @ColumnInfo(name = "Name")
    var currencyName:String="N/A",
    @ColumnInfo(name= "Abbreviation")
    var currencyAbbreviation:String="N/A",
    @ColumnInfo(name= "Symbol")
    var currencySymbol:String="N/A",
    var image:Int=0,
    var btcRate:Double=0.00,
    var ethRate:Double=0.00
)
{



}
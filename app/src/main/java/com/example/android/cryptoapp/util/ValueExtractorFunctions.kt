package com.example.android.cryptoapp.util
import com.example.android.cryptoapp.R
import com.example.android.cryptoapp.currency_data.*

//Checks the spinner and returns the abbreviation and image resource of the currency selected
  fun getCurrencyAbbrFromSpinner(position: Int): CurrencyAbbreviation {
    val currencyAbbreviation: CurrencyAbbreviation
    when (position) {
        1 -> {
            currencyAbbreviation = CurrencyAbbreviation.AUSTRALIA_DOLLAR
        }
        2 -> {
            currencyAbbreviation = CurrencyAbbreviation.EGYPT_POUND
        }
        3 -> {
            currencyAbbreviation =  CurrencyAbbreviation.GREAT_BRITAIN_POUNDS
        }
        4 -> {
            currencyAbbreviation =  CurrencyAbbreviation.GERMAN_EURO
        }
        5 -> {
            currencyAbbreviation =  CurrencyAbbreviation.GEORGIA_LARI
        }
        6 -> {
            currencyAbbreviation =  CurrencyAbbreviation.GHANA_NEW_CEDI
        }
        7 -> {
            currencyAbbreviation =  CurrencyAbbreviation.HONG_KONG_DOLLAR
        }
        8 -> {
            currencyAbbreviation =  CurrencyAbbreviation.ISRAEL_NEW_SHEKEL
        }
        9 -> {
            currencyAbbreviation = CurrencyAbbreviation.JAMAICA_DOLLAR
        }
        10 -> {
            currencyAbbreviation =  CurrencyAbbreviation.JAPAN_YEN
        }
        11 -> {
            currencyAbbreviation =  CurrencyAbbreviation.MALAYSIA_RINGGIT
        }
        12 -> {
            currencyAbbreviation =  CurrencyAbbreviation.NIGERIA_NAIRA
        }
        13 -> {
            currencyAbbreviation = CurrencyAbbreviation.PHILIPPINES_PESO
        }
        14 -> {
            currencyAbbreviation =  CurrencyAbbreviation.QATAR_RIAL
        }
        15 -> {
            currencyAbbreviation =  CurrencyAbbreviation.RUSSIAN_ROUBLE
        }
        16 -> {
            currencyAbbreviation =  CurrencyAbbreviation.SOUTH_AFRICA_RAND
        }
        17 -> {
            currencyAbbreviation =  CurrencyAbbreviation.SWITZERLAND_FRANC
        }
        18 -> {
            currencyAbbreviation = CurrencyAbbreviation.TAIWAN_DOLLAR
        }
        19 -> {
            currencyAbbreviation =  CurrencyAbbreviation.THAILAND_BAHT
        }
        20 -> {
            currencyAbbreviation =  CurrencyAbbreviation.USA_DOLLAR
        }
        else -> currencyAbbreviation = CurrencyAbbreviation.NONE
    }
    return currencyAbbreviation
}

 fun getCurrencyImage(currencyAbbreviation: CurrencyAbbreviation): Int {
    var image:Int=R.mipmap.icon
    when (currencyAbbreviation) {
        CurrencyAbbreviation.AUSTRALIA_DOLLAR -> {
             image = R.drawable.aud
                      }
        CurrencyAbbreviation.EGYPT_POUND -> {

           image = R.drawable.egp
        }
        CurrencyAbbreviation.GREAT_BRITAIN_POUNDS -> {

            image = R.drawable.gbp
        }
        CurrencyAbbreviation.GERMAN_EURO -> {

           image = R.drawable.eur
        }
        CurrencyAbbreviation.GEORGIA_LARI -> {

           image = R.drawable.gel
        }
        CurrencyAbbreviation.GHANA_NEW_CEDI -> {

           image = R.drawable.ghs
        }
        CurrencyAbbreviation.HONG_KONG_DOLLAR -> {

           image = R.drawable.hdk
        }
        CurrencyAbbreviation.ISRAEL_NEW_SHEKEL -> {

           image = R.drawable.ils
        }
        CurrencyAbbreviation.JAMAICA_DOLLAR -> {

            image = R.drawable.jmd
        }
        CurrencyAbbreviation.JAPAN_YEN -> {

           image = R.drawable.jpy
        }
        CurrencyAbbreviation.MALAYSIA_RINGGIT -> {

           image = R.drawable.myr
        }
        CurrencyAbbreviation.NIGERIA_NAIRA -> {

           image = R.drawable.ngn
        }
        CurrencyAbbreviation.PHILIPPINES_PESO -> {

           image = R.drawable.php
        }
        CurrencyAbbreviation.QATAR_RIAL -> {

           image = R.drawable.qar
        }
        CurrencyAbbreviation.RUSSIAN_ROUBLE -> {

           image = R.drawable.rub
        }
        CurrencyAbbreviation.SOUTH_AFRICA_RAND -> {

            image = R.drawable.zar
        }
        CurrencyAbbreviation.SWITZERLAND_FRANC -> {

           image = R.drawable.chf
        }
        CurrencyAbbreviation.TAIWAN_DOLLAR -> {

           image = R.drawable.twd
        }
        CurrencyAbbreviation.THAILAND_BAHT -> {

           image = R.drawable.thb
        }
        CurrencyAbbreviation.USA_DOLLAR -> {

           image = R.drawable.usd
        }
        else -> R.mipmap.icon
    }
    return image
}

//confirm BTC rates after putting  the currency type as parameter of this method
fun getBtcRate(currency: CurrencyAbbreviation?,btc: Btc?): Double {

    return when (currency) {
        CurrencyAbbreviation.AUSTRALIA_DOLLAR ->    btc?.AUD ?: 0.00
        CurrencyAbbreviation.EGYPT_POUND ->         btc?.EGP ?: 0.00
        CurrencyAbbreviation.GREAT_BRITAIN_POUNDS ->btc?.GBP ?: 0.00
        CurrencyAbbreviation.GERMAN_EURO->          btc?.EUR ?: 0.00
        CurrencyAbbreviation.GEORGIA_LARI->         btc?.GEL ?: 0.00
        CurrencyAbbreviation.GHANA_NEW_CEDI ->      btc?.GHS ?: 0.00
        CurrencyAbbreviation.HONG_KONG_DOLLAR ->    btc?.HKD ?: 0.00
        CurrencyAbbreviation.ISRAEL_NEW_SHEKEL ->   btc?.ILS ?: 0.00
        CurrencyAbbreviation.JAMAICA_DOLLAR ->      btc?.JMD ?: 0.00
        CurrencyAbbreviation.JAPAN_YEN ->           btc?.JPY ?: 0.00
        CurrencyAbbreviation.MALAYSIA_RINGGIT ->    btc?.MYR ?: 0.00
        CurrencyAbbreviation.NIGERIA_NAIRA ->       btc?.NGN ?: 0.00
        CurrencyAbbreviation.PHILIPPINES_PESO ->    btc?.PHP ?: 0.00
        CurrencyAbbreviation.QATAR_RIAL ->          btc?.QAR ?: 0.00
        CurrencyAbbreviation.RUSSIAN_ROUBLE ->      btc?.RUB ?: 0.00
        CurrencyAbbreviation.SOUTH_AFRICA_RAND ->   btc?.ZAR ?: 0.00
        CurrencyAbbreviation.SWITZERLAND_FRANC->    btc?.CHF ?: 0.00
        CurrencyAbbreviation.TAIWAN_DOLLAR->        btc?.TWD ?: 0.00
        CurrencyAbbreviation.THAILAND_BAHT ->       btc?.THB ?: 0.00
        CurrencyAbbreviation.USA_DOLLAR->           btc?.USD ?: 0.00
        else -> 0.0
    }
}

//confirm ETH rates after putting the currency type as parameter of this method
 fun getEthRate(currency: CurrencyAbbreviation?,eth: Eth?): Double {
    val ethRates: Double
    var currencySymbol: CurrencySymbol = CurrencySymbol.NONE
    when (currency) {

        CurrencyAbbreviation.AUSTRALIA_DOLLAR-> {
            ethRates = eth?.AUD ?: 0.00
        }
        CurrencyAbbreviation.EGYPT_POUND -> {
            ethRates =eth?.EGP ?: 0.00
        }
        CurrencyAbbreviation.GERMAN_EURO -> {
            ethRates =  eth?.EUR ?: 0.00
        }
        CurrencyAbbreviation.GREAT_BRITAIN_POUNDS -> {
            ethRates = eth?.GBP ?: 0.00
        }
        CurrencyAbbreviation.GEORGIA_LARI -> {
            ethRates = eth?.GEL ?: 0.00
        }
        CurrencyAbbreviation.GHANA_NEW_CEDI -> {
            ethRates = eth?.GHS ?: 0.00
        }
        CurrencyAbbreviation.HONG_KONG_DOLLAR-> {
            ethRates = eth?.HKD ?: 0.00
        }
        CurrencyAbbreviation.ISRAEL_NEW_SHEKEL-> {
            ethRates = eth?.ILS ?: 0.00
        }
        CurrencyAbbreviation.JAMAICA_DOLLAR -> {
            ethRates = eth?.JMD ?: 0.00
        }
        CurrencyAbbreviation.JAPAN_YEN -> {
            ethRates = eth?.JPY ?: 0.00
        }
        CurrencyAbbreviation.MALAYSIA_RINGGIT -> {
            ethRates = eth?.MYR ?: 0.00
        }
        CurrencyAbbreviation.NIGERIA_NAIRA -> {
            ethRates = eth?.NGN ?: 0.00
        }
        CurrencyAbbreviation.PHILIPPINES_PESO -> {
            ethRates = eth?.PHP ?: 0.00
        }
        CurrencyAbbreviation.QATAR_RIAL -> {
            ethRates = eth?.QAR ?: 0.00
        }
        CurrencyAbbreviation.RUSSIAN_ROUBLE -> {
            ethRates = eth?.RUB ?: 0.00
        }
        CurrencyAbbreviation.SOUTH_AFRICA_RAND -> {
            ethRates = eth?.ZAR ?: 0.00
        }
        CurrencyAbbreviation.SWITZERLAND_FRANC-> {
            ethRates = eth?.CHF ?: 0.00
        }
        CurrencyAbbreviation.TAIWAN_DOLLAR -> {
            ethRates = eth?.TWD ?: 0.00
        }
        CurrencyAbbreviation.THAILAND_BAHT -> {
            ethRates = eth?.THB ?: 0.00
        }
        CurrencyAbbreviation.USA_DOLLAR -> {
            ethRates = eth?.USD ?: 0.00
        }
        else -> ethRates = 0.0
    }

    return ethRates
}

 fun getSymbol(currency: CurrencyAbbreviation?): CurrencySymbol {
    var currencySymbol: CurrencySymbol = CurrencySymbol.NONE
    when (currency) {

        CurrencyAbbreviation.AUSTRALIA_DOLLAR-> {
            currencySymbol = CurrencySymbol.AUD
        }
        CurrencyAbbreviation.EGYPT_POUND -> {
            currencySymbol =  CurrencySymbol.EGP
        }
        CurrencyAbbreviation.GERMAN_EURO -> {
            currencySymbol =   CurrencySymbol.EUR
        }
        CurrencyAbbreviation.GREAT_BRITAIN_POUNDS -> {
            currencySymbol =   CurrencySymbol.GBP
        }
        CurrencyAbbreviation.GEORGIA_LARI -> {
            currencySymbol =   CurrencySymbol.GEL
        }
        CurrencyAbbreviation.GHANA_NEW_CEDI -> {
            currencySymbol =   CurrencySymbol.EUR
        }
        CurrencyAbbreviation.HONG_KONG_DOLLAR-> {
            currencySymbol =   CurrencySymbol.HKD
        }
        CurrencyAbbreviation.ISRAEL_NEW_SHEKEL-> {
            currencySymbol =   CurrencySymbol.ILS
        }
        CurrencyAbbreviation.JAMAICA_DOLLAR -> {
            currencySymbol =   CurrencySymbol.JMD
        }
        CurrencyAbbreviation.JAPAN_YEN -> {
            currencySymbol =   CurrencySymbol.JPY
        }
        CurrencyAbbreviation.MALAYSIA_RINGGIT -> {
            currencySymbol =   CurrencySymbol.MYR
        }
        CurrencyAbbreviation.NIGERIA_NAIRA -> {
            currencySymbol =   CurrencySymbol.NGN
        }
        CurrencyAbbreviation.PHILIPPINES_PESO -> {
            currencySymbol =   CurrencySymbol.PHP
        }
        CurrencyAbbreviation.QATAR_RIAL -> {
            currencySymbol =   CurrencySymbol.QAR
        }
        CurrencyAbbreviation.RUSSIAN_ROUBLE -> {
           currencySymbol =   CurrencySymbol.RUB
        }
        CurrencyAbbreviation.SOUTH_AFRICA_RAND -> {
            currencySymbol =   CurrencySymbol.ZAR
        }
        CurrencyAbbreviation.SWITZERLAND_FRANC-> {
            currencySymbol =   CurrencySymbol.CHF
        }
        CurrencyAbbreviation.TAIWAN_DOLLAR -> {
            currencySymbol =   CurrencySymbol.TWD
        }
        CurrencyAbbreviation.THAILAND_BAHT -> {
            currencySymbol =   CurrencySymbol.THB
        }
        CurrencyAbbreviation.USA_DOLLAR -> {
            currencySymbol =   CurrencySymbol.USD
        }
        else -> CurrencySymbol.NONE
    }

    return currencySymbol
}


fun getCurrencyName(currency: CurrencyAbbreviation?): CurrencyName {
    var currencyName: CurrencyName = CurrencyName.NONE
    when (currency) {

        CurrencyAbbreviation.AUSTRALIA_DOLLAR-> {
            currencyName = CurrencyName.AUSTRALIA_DOLLAR
        }
        CurrencyAbbreviation.EGYPT_POUND -> {
            currencyName =  CurrencyName.EGYPT_POUND
        }
        CurrencyAbbreviation.GERMAN_EURO -> {
            currencyName =   CurrencyName.GERMAN_EURO
        }
        CurrencyAbbreviation.GREAT_BRITAIN_POUNDS -> {
            currencyName =   CurrencyName.GREAT_BRITAIN_POUNDS
        }
        CurrencyAbbreviation.GEORGIA_LARI -> {
            currencyName =   CurrencyName.GEORGIA_LARI
        }
        CurrencyAbbreviation.GHANA_NEW_CEDI -> {
            currencyName =   CurrencyName.GHANA_NEW_CEDI
        }
        CurrencyAbbreviation.HONG_KONG_DOLLAR-> {
            currencyName =   CurrencyName.HONG_KONG_DOLLAR
        }
        CurrencyAbbreviation.ISRAEL_NEW_SHEKEL-> {
            currencyName =   CurrencyName.ISRAEL_NEW_SHEKEL
        }
        CurrencyAbbreviation.JAMAICA_DOLLAR -> {
            currencyName = CurrencyName.JAMAICA_DOLLAR
        }
        CurrencyAbbreviation.JAPAN_YEN -> {
            currencyName =   CurrencyName.JAPAN_YEN
        }
        CurrencyAbbreviation.MALAYSIA_RINGGIT -> {
            currencyName =   CurrencyName.MALAYSIA_RINGGIT
        }
        CurrencyAbbreviation.NIGERIA_NAIRA -> {
            currencyName =   CurrencyName.NIGERIA_NAIRA
        }
        CurrencyAbbreviation.PHILIPPINES_PESO -> {
            currencyName =   CurrencyName.PHILIPPINES_PESO
        }
        CurrencyAbbreviation.QATAR_RIAL -> {
            currencyName =   CurrencyName.QATAR_RIAL
        }
        CurrencyAbbreviation.RUSSIAN_ROUBLE -> {
            currencyName =   CurrencyName.RUSSIAN_ROUBLE
        }
        CurrencyAbbreviation.SOUTH_AFRICA_RAND -> {
            currencyName =   CurrencyName.SOUTH_AFRICA_RAND
        }
        CurrencyAbbreviation.SWITZERLAND_FRANC-> {
            currencyName =   CurrencyName.SWITZERLAND_FRANC
        }
        CurrencyAbbreviation.TAIWAN_DOLLAR -> {
            currencyName =   CurrencyName.TAIWAN_DOLLAR
        }
        CurrencyAbbreviation.THAILAND_BAHT -> {
            currencyName =   CurrencyName.THAILAND_BAHT
        }
        CurrencyAbbreviation.USA_DOLLAR -> {
            currencyName =   CurrencyName.USA_DOLLAR
        }
        else -> CurrencyName.NONE
    }

    return currencyName
}


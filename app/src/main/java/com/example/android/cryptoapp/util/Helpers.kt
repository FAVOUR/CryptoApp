package com.example.android.cryptoapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat

object Helpers {
     const val CRYPTOCOMPARE_BASE_URL = "https://min-api.cryptocompare.com/data/"

     @JvmStatic
     fun formatAndReturnString(data :Double): String {
          val  decimalFormatter =DecimalFormat()
          decimalFormatter.isGroupingUsed = true
          decimalFormatter.maximumIntegerDigits = 10
          decimalFormatter.maximumFractionDigits = 3
         return decimalFormatter.format(data)
     }


     @JvmStatic
     @BindingAdapter("currencyLogo")
      fun ImageView.currencyLogo(imageResource:Int){
          setImageResource(imageResource)
     }
}
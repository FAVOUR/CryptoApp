package com.example.android.cryptoapp.ui.adapters.binding_adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.cryptoapp.R


object ConversionBindingAdapter { //TODO CHANGE TO BOOLEAN

    @JvmStatic
     @BindingAdapter("text_color")
     fun TextView.setColorForTotalAmount(isEtn: String){
      val color =  if(isEtn=="0"){
             R.color.second_color
         }else{
            R.color.first_color
         }
                setTextColor(color)
     }
 }
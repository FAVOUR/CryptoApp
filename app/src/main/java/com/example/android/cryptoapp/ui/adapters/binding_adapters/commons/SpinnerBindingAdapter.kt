package com.example.android.cryptoapp.ui.adapters.binding_adapters.commons

import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.example.android.cryptoapp.currency_data.CurrencyAbbreviation
import com.example.android.cryptoapp.util.getCurrencyAbbrFromSpinner

object SpinnerBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["selectedValue","selectedValueAttrChanged"], requireAll = false)
    fun Spinner.setSelectedValue( itemSelected:String?, inverseBindingListener: InverseBindingListener){

        onItemSelectedListener = object : AdapterView.OnItemSelectedListener{


            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                inverseBindingListener.onChange()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        if(itemSelected != null){
              val position=   (adapter as ArrayAdapter<String>).getPosition(itemSelected)
               setSelection(position,true)
           }
    }

    @JvmStatic
    @InverseBindingAdapter(attribute ="selectedValue",event ="selectedValueAttrChanged" )
      fun Spinner.getSelectedValue():CurrencyAbbreviation{
            val  position=(adapter as ArrayAdapter<String>).getPosition(selectedItem as String)
            return  getCurrencyAbbrFromSpinner(position)

    }

}
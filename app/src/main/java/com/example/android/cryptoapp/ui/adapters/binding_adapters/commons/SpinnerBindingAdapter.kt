package com.example.android.cryptoapp.ui.adapters.binding_adapters.commons

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.example.android.cryptoapp.data.model.CurrencyAbbreviation
import com.example.android.cryptoapp.util.getCurrencyAbbrFromSpinner

object SpinnerBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["selectedValue","selectedValueAttrChanged"], requireAll = false)
    fun Spinner.setSelectedValue( itemSelected:CurrencyAbbreviation?, inverseBindingListener: InverseBindingListener){

        onItemSelectedListener = object : AdapterView.OnItemSelectedListener{


            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(itemSelected != null){
                    setSelection(position,true)
                }

                inverseBindingListener.onChange()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


    }

    @JvmStatic
    @InverseBindingAdapter(attribute ="selectedValue",event ="selectedValueAttrChanged" )
      fun Spinner.getSelectedValue():CurrencyAbbreviation{
            val  position=(adapter as ArrayAdapter<String>).getPosition(selectedItem as String)
            return  getCurrencyAbbrFromSpinner(position)
    }



}
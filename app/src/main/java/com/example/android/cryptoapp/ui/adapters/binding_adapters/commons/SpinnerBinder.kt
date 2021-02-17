package com.example.android.cryptoapp.ui.adapters.binding_adapters.commons

import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

object SpinnerBinder {

    @JvmStatic
    @BindingAdapter(value = ["selectedValue","selectedValueAttributeChange"])
    fun Spinner.setSelectedValue( itemSelected:String, inverseBindingListener: InverseBindingListener){

        onItemSelectedListener = object : AdapterView.OnItemSelectedListener{


            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                inverseBindingListener.onChange()
            }




            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        if(itemSelected != null){
              val position=   (adapter as ArrayAdapter<String>).getPosition(itemSelected)
               setSelection(position,true)
           }
    }

    @JvmStatic
    @InverseBindingAdapter(attribute ="selectedValue",event ="selectedValueAttributeChange" )
      fun Spinner.getSelectedValue(itemSelected:String):String{
            return selectedItem as String
    }

}
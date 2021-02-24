package com.example.android.cryptoapp.viewmodel

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel

abstract  class BaseObservableViewModel():ViewModel(),Observable {

    private val registory : PropertyChangeRegistry = PropertyChangeRegistry()

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        registory.remove(callback)

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
       registory.add(callback)
    }

      fun notifyChange(){
       registory.notifyCallbacks(this,0,null)
      }

     fun notifyPropertyChanged(fieldId:Int){
         registory.notifyCallbacks(this,fieldId,null)
     }
}
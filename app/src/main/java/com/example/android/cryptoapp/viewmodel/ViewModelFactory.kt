package com.example.android.cryptoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.cryptoapp.database.CurrencyDao

class ViewModelFactory(val currencyDao: CurrencyDao):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST")
        return with(modelClass) {
             when {

                 isAssignableFrom(EditorViewModel::class.java) -> EditorViewModel(currencyDao = currencyDao)
                 isAssignableFrom(ListViewModel::class.java) -> ListViewModel(currencyDao = currencyDao)


                 else -> throw Throwable("the class $modelClass is not an instance of viewmodel ")
             }

         }as T
    }
}
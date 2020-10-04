package com.example.android.cryptoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.cryptoapp.data.source.local.db.CurrencyDao
import com.example.android.cryptoapp.data.source.repository.CryptoRepository

class ViewModelFactory(val currencyDao: CurrencyDao,val repository: CryptoRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST")
        return with(modelClass) {
             when {

                 isAssignableFrom(EditorViewModel::class.java) -> EditorViewModel(currencyDao = currencyDao,repository = repository)
                 isAssignableFrom(ListViewModel::class.java) -> ListViewModel(currencyDao = currencyDao,repository = repository)
                 isAssignableFrom(ConversionViewmodel::class.java) -> ConversionViewmodel(currencyDao = currencyDao,repository = repository)


                 else -> throw Throwable("the class $modelClass is not an instance of viewmodel ")
             }

         }as T
    }
}
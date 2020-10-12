package com.example.android.cryptoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.cryptoapp.data.source.local.db.CurrencyDao
import com.example.android.cryptoapp.data.source.repository.CryptoRepository
import javax.inject.Inject
import javax.inject.Provider

class  ViewModelFactory @Inject constructor(val creators: MutableMap<Class<out ViewModel>, Provider<ViewModel>>,val currencyDao: CurrencyDao, val repository: CryptoRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {


        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        @Suppress("UNCHECKED_CAST")
        return creator.get() as T

     /*   var creator = creators[modelClass]

         if (creator != null){
           for ((key,value) in creators) {
//               getViewmodelClass(modelClass)
               creator=  value
           }
        }
         else{

             throw Throwable("the class $modelClass is not an instance of viewmodel ")
        }

        @Suppress("UNCHECKED_CAST")
        // return the Provider
        return try {
            (creator!!.get() as T?)!!
        } catch (e: Exception) {
            throw RuntimeException(e)
        }*/
    }

//    private fun <T : ViewModel?> ViewModelFactory.getViewmodelClass(modelClass: Class<T>) {
//        @Suppress("UNCHECKED_CAST")
//        with(modelClass) {
//            when {
//
//                isAssignableFrom(EditorViewModel::class.java) -> EditorViewModel(currencyDao = currencyDao, repository = repository)
//                isAssignableFrom(ListViewModel::class.java) -> ListViewModel(currencyDao = currencyDao, repository = repository)
//                isAssignableFrom(ConversionViewmodel::class.java) -> ConversionViewmodel(currencyDao = currencyDao, repository = repository)
//
//
//                else -> throw Throwable("the class $modelClass is not an instance of viewmodel ")
//            }
//        }
//    }
}
package com.example.android.cryptoapp.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.cryptoapp.di.annotations.ViewModelKey
import com.example.android.cryptoapp.viewmodel.ConversionViewmodel
import com.example.android.cryptoapp.viewmodel.EditorViewModel
import com.example.android.cryptoapp.viewmodel.ListViewModel
import com.example.android.cryptoapp.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ConversionViewmodel::class)
    abstract  fun bindsConversionViewModel(conversionViewmodel: ConversionViewmodel):ViewModel


      @Binds
      @IntoMap
      @ViewModelKey(ListViewModel::class)
      abstract  fun bindsListViewModel(listViewModel: ListViewModel):ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(EditorViewModel::class)
    abstract  fun bindsEditorViewModel(editorViewModel: EditorViewModel):ViewModel


    //Creates the ViewModelFactory
    @Binds
    abstract  fun bindsFactory(viewModelFactory: ViewModelFactory):ViewModelProvider.Factory

}
package com.example.android.cryptoapp.di.module

import com.example.android.cryptoapp.data.source.ILocalCryptoRatesDataSource
import com.example.android.cryptoapp.data.source.IRemoteCryptoRateDataSource
import com.example.android.cryptoapp.data.source.local.LocalCryptoRatesDataSource
import com.example.android.cryptoapp.data.source.remote.RemoteCryptoRateDataSource
import com.example.android.cryptoapp.data.source.repository.CryptoRepository
import com.example.android.cryptoapp.data.source.repository.DefaultCryptoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
abstract class ActivityModule { //TODO To fit this name you have to instantiate at the activity level use activity viewModel extension and then make the fragments get the instance from required activity method

    //Provide an instance of the CryptoRepository
    @Binds
    abstract fun bindsRepositoryInstance(defaultCryptoRepository:DefaultCryptoRepository): CryptoRepository

    //Provide an instance of the CryptoRatesDataSource
    @Binds
    abstract fun bindsCryptoRatesDataInstance(remoteCryptoRateDataSource: RemoteCryptoRateDataSource): IRemoteCryptoRateDataSource


    //Provide an instance of the CryptoRatesDataSource
    @Binds
    abstract fun bindsCryptoRatesInstance(localCryptoRatesDataSource: LocalCryptoRatesDataSource): ILocalCryptoRatesDataSource

}
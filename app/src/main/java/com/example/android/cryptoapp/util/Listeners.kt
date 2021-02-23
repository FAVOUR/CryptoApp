package com.example.android.cryptoapp.util

import com.example.android.cryptoapp.domain.model.CryptoCurrencyRates

class Listeners {

    interface ListItemClickListener {
        fun onListItemClicked(result: CryptoCurrencyRates)
    }


    interface LongItemClickedListener{
        fun onLongItemClickedListener(result: CryptoCurrencyRates)
    }
}
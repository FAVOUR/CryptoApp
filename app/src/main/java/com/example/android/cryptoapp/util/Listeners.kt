package com.example.android.cryptoapp.util

import com.example.android.cryptoapp.domain.model.CryptoCurrencyRates

class Listeners {

    interface ListItemClickListiner {
        fun onListItemClicked(result: CryptoCurrencyRates)
    }


    interface LongItemClickedListener{
        fun onLongItemClickedListener(itemId:Long)
    }
}
package com.example.android.cryptoapp.currency_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by OZMA NIG COM LTD on 05-Oct-17.
 */
class JsonResponse {
    @SerializedName("BTC")
    @Expose
    val bTC: Btc? = null

    @SerializedName("ETH")
    @Expose
    val eTH: Eth? = null
}
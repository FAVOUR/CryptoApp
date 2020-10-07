package com.example.android.cryptoapp.currency_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by OZMA NIG COM LTD on 05-Oct-17.
 */
//@JsonClass(generateAdapter = true)
data class JsonResponse (
    @Json(name ="BTC")
//    @Expose
    val bTC: Btc? = null,

    @Json(name = "ETH")
//    @Expose
    val eTH: Eth? = null
)
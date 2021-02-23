package com.example.android.cryptoapp.currency_data

import com.example.android.cryptoapp.util.DataTransferObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by OZMA NIG COM LTD on 05-Oct-17.
 */
//@JsonClass(generateAdapter = true)
data class CryptoRatesResponse (
    @Json(name ="BTC")
//    @Expose
    val bTC: Btc? = null,

    @Json(name = "ETH")
//    @Expose
    val eTH: Eth? = null
)
//    : DataTransferObject<JsonResponse> {  //With this You can now use DataTransferObject<T> in for example getCryptoRate()method do the database conversion
//    override fun JsonResponse.asDataBaseModel() {
//        TODO("Not yet implemented")
//    }
//
//    override fun List<JsonResponse>.asDataBaseModel() {
//        TODO("Not yet implemented")
//    }
//}
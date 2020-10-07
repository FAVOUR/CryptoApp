package com.example.android.cryptoapp.currency_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Btc (
 @Json(name ="AUD")
//    @Expose
    val AUD: Double? = null,

 @Json(name ="EGP")
//    @Expose
    val EGP: Double? = null,

 @Json(name ="GBP")
//    @Expose
    val GBP: Double? = null,

 @Json(name ="EUR")
//    @Expose
    val EUR: Double? = null,

 @Json(name ="GEL")
//    @Expose
    val GEL: Double? = null,

 @Json(name ="GHS")
//    @Expose
    val GHS: Double? = null,

 @Json(name ="HKD")
//    @Expose
    val HKD: Double? = null,

 @Json(name ="ILS")
//    @Expose
    val ILS: Double? = null,

 @Json(name ="JMD")
//    @Expose
    val JMD: Double? = null,

 @Json(name ="JPY")
//    @Expose
    val JPY: Double? = null,

 @Json(name ="MYR")
//    @Expose
    val MYR: Double? = null,

 @Json(name ="NGN")
//    @Expose
    val NGN: Double? = null,

 @Json(name ="PHP")
//    @Expose
    val PHP: Double? = null,

 @Json(name ="QAR")
//    @Expose
    val QAR: Double? = null,

 @Json(name ="RUB")
//    @Expose
    val RUB: Double? = null,

 @Json(name ="ZAR")
//    @Expose
    val ZAR: Double? = null,

 @Json(name ="CHF")
//    @Expose
    val CHF: Double? = null,

 @Json(name ="TWD")
//    @Expose
    val TWD: Double? = null,

 @Json(name ="THB")
//    @Expose
    val THB: Double? = null,

 @Json(name ="USD")
//    @Expose
    val USD: Double? = null

)
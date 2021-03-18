package com.example.android.cryptoapp.data.model

import com.squareup.moshi.Json

//@JsonClass(generateAdapter = true)

data class Eth (
 @Json(name ="AUD")
//    @Expose
    val AUD: Double? = 0.00,

 @Json(name ="EGP")
//    @Expose
    val EGP: Double? = 0.00,

 @Json(name ="GBP")
//    @Expose
    val GBP: Double? = 0.00,

 @Json(name ="EUR")
//    @Expose
    val EUR: Double? = 0.00,

 @Json(name ="GEL")
//    @Expose
    val GEL: Double? = 0.00,

 @Json(name ="GHS")
//    @Expose
    val GHS: Double? = 0.00,

 @Json(name ="HKD")
//    @Expose
    val HKD: Double? = 0.00,

 @Json(name ="ILS")
//    @Expose
    val ILS: Double? = 0.00,

 @Json(name ="JMD")
//    @Expose
    val JMD: Double? = 0.00,

 @Json(name ="JPY")
//    @Expose
    val JPY: Double? = 0.00,

 @Json(name ="MYR")
//    @Expose
    val MYR: Double? = 0.00,

 @Json(name ="NGN")
//    @Expose
    val NGN: Double? = 0.00,

 @Json(name ="PHP")
//    @Expose
    val PHP: Double? = 0.00,

 @Json(name ="QAR")
//    @Expose
    val QAR: Double? = 0.00,

 @Json(name ="RUB")
//    @Expose
    val RUB: Double? = 0.00,

 @Json(name ="ZAR")
//    @Expose
    val ZAR: Double? = 0.00,

 @Json(name ="CHF")
//    @Expose
    val CHF: Double? = 0.00,

 @Json(name ="TWD")
//    @Expose
    val TWD: Double? = 0.00,

 @Json(name ="THB")
//    @Expose
    val THB: Double? = 0.00,

 @Json(name ="USD")
//    @Expose
    val USD: Double? = 0.00

 )


package com.example.android.cryptoapp.currency_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Eth {
    @SerializedName("AUD")
    @Expose
    val AUD: Double? = null

    @SerializedName("EGP")
    @Expose
    val EGP: Double? = null

    @SerializedName("GBP")
    @Expose
    val GBP: Double? = null

    @SerializedName("EUR")
    @Expose
    val EUR: Double? = null

    @SerializedName("GEL")
    @Expose
    val GEL: Double? = null

    @SerializedName("GHS")
    @Expose
    val GHS: Double? = null

    @SerializedName("HKD")
    @Expose
    val HKD: Double? = null

    @SerializedName("ILS")
    @Expose
    val ILS: Double? = null

    @SerializedName("JMD")
    @Expose
    val JMD: Double? = null

    @SerializedName("JPY")
    @Expose
    val JPY: Double? = null

    @SerializedName("MYR")
    @Expose
    val MYR: Double? = null

    @SerializedName("NGN")
    @Expose
    val NGN: Double? = null

    @SerializedName("PHP")
    @Expose
    val PHP: Double? = null

    @SerializedName("QAR")
    @Expose
    val QAR: Double? = null

    @SerializedName("RUB")
    @Expose
    val RUB: Double? = null

    @SerializedName("ZAR")
    @Expose
    val ZAR: Double? = null

    @SerializedName("CHF")
    @Expose
    val CHF: Double? = null

    @SerializedName("TWD")
    @Expose
    val TWD: Double? = null

    @SerializedName("THB")
    @Expose
    val THB: Double? = null

    @SerializedName("USD")
    @Expose
    val USD: Double? = null


    val aUDSymbol: String
        get() = "$"

    val cHFSymbol: String
        get() = "₣"

    val eGPSymbol: String
        get() = "£"

    val eURSymbol: String
        get() = "€"

    val gBPSymbol: String
        get() = "£"

    val gELSymbol: String
        get() = "ლ"

    val gHSSymbol: String
        get() = "₵"

    val hKDSymbol: String
        get() = "$"

    val iLSSymbol: String
        get() = "₪"

    val jMDSymbol: String
        get() = "$"

    val jPYSymbol: String
        get() = "¥"

    val mYRSymbol: String
        get() = "RM"

    val nGNSymbol: String
        get() = "₦"

    val pHPSymbol: String
        get() = "₱"

    val qARSymbol: String
        get() = "ر.ق"

    val rUBSymbol: String
        get() = "р"

    val tHBSymbol: String
        get() = "฿"

    val tWDSymbol: String
        get() = "$"

    val uSDSymbol: String
        get() = "$"

    val zARSymbol: String
        get() = "R"
}
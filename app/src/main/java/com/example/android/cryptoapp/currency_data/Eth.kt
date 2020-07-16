package com.example.android.cryptoapp.currency_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Eth {
    @SerializedName("AUD")
    @Expose
    val aUD: Double? = null

    @SerializedName("EGP")
    @Expose
    val eGP: Double? = null

    @SerializedName("GBP")
    @Expose
    val gBP: Double? = null

    @SerializedName("EUR")
    @Expose
    val eUR: Double? = null

    @SerializedName("GEL")
    @Expose
    val gEL: Double? = null

    @SerializedName("GHS")
    @Expose
    val gHS: Double? = null

    @SerializedName("HKD")
    @Expose
    val hKD: Double? = null

    @SerializedName("ILS")
    @Expose
    val iLS: Double? = null

    @SerializedName("JMD")
    @Expose
    val jMD: Double? = null

    @SerializedName("JPY")
    @Expose
    val jPY: Double? = null

    @SerializedName("MYR")
    @Expose
    val mYR: Double? = null

    @SerializedName("NGN")
    @Expose
    val nGN: Double? = null

    @SerializedName("PHP")
    @Expose
    val pHP: Double? = null

    @SerializedName("QAR")
    @Expose
    val qAR: Double? = null

    @SerializedName("RUB")
    @Expose
    val rUB: Double? = null

    @SerializedName("ZAR")
    @Expose
    val zAR: Double? = null

    @SerializedName("CHF")
    @Expose
    val cHF: Double? = null

    @SerializedName("TWD")
    @Expose
    val tWD: Double? = null

    @SerializedName("THB")
    @Expose
    val tHB: Double? = null

    @SerializedName("USD")
    @Expose
    val uSD: Double? = null

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
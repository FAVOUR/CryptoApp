package com.example.android.cryptoapp.ui.adapters.binding_adapters.commons.component

import java.text.DecimalFormat

object Converters {

    @JvmStatic
    fun formatAndReturnString(data :Double): String {
        val  decimalFormatter = DecimalFormat()
        decimalFormatter.isGroupingUsed = true
        decimalFormatter.maximumIntegerDigits = 10
        decimalFormatter.maximumFractionDigits = 3
        return decimalFormatter.format(data)
    }
}
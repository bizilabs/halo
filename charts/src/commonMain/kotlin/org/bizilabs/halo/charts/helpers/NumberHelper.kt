package org.bizilabs.halo.charts.helpers

import kotlin.math.pow

fun Float.format(decimalPlaces: Int = 1): String {
    val factor = 10.0.pow(decimalPlaces)
    val roundedValue = (this * factor).toInt() / factor
    return roundedValue.toString()
}

fun Double.format(decimalPlaces: Int = 1): String {
    val factor = 10.0.pow(decimalPlaces)
    val roundedValue = (this * factor).toInt() / factor
    return roundedValue.toString()
}

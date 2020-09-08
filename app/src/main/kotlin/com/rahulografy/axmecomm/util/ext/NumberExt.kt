package com.rahulografy.axmecomm.util.ext

import java.text.NumberFormat
import java.util.*

/**
 * Returns formatted value of specified amount number with appended currency
 */
fun Number?.amountize(locale: Locale = Locale("en", "eu")): String? =
    NumberFormat
        .getCurrencyInstance(locale)
        .format(this)
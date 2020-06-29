package no.mhl.swap.util.extension

import java.util.*

fun String.capitalize() =
    "${this.substring(0, 1).toUpperCase(Locale.ROOT)}${this.substring(1)}"
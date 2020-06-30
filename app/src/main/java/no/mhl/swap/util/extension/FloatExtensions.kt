package no.mhl.swap.util.extension

import android.content.Context
import android.util.TypedValue

fun Float.fromDpToFloat(context: Context) =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        context.resources.displayMetrics
    )
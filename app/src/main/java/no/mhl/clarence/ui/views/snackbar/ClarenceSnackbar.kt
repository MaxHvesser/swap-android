package no.mhl.clarence.ui.views.snackbar

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun makeClarenceSnackbar(parent: View, text: String, duration: Int): Snackbar {
    return Snackbar
        .make(parent, text, duration)
}
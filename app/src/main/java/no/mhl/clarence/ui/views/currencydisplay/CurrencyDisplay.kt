package no.mhl.clarence.ui.views.currencydisplay

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import no.mhl.clarence.R

class CurrencyDisplay(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    // region View Properties
    private val value: TextView by lazy { findViewById<TextView>(R.id.currency_display_value) }
    // endregion

    // region Initialisation
    init {
        View.inflate(context, R.layout.view_currency_display, this)
        setupViews()
    }
    // endregion

    // region Setup Views
    private fun setupViews() { }
    // endregion

    // region Value Text IO
    fun appendValue(value: String) {
        if (this.value.text.toString() == "0") {
            this.value.text = value
        } else {
            this.value.append(value)
        }
    }

    fun backspaceValue() {
        if (value.text.length == 1) {
            value.text = "0"
        } else {
            value.text = value.text.substring(0, value.text.length -1)
        }
    }

    fun clearValue() {
        value.text = "0"
    }
    // endregion

}
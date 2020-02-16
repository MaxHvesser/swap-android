package no.mhl.clarence.ui.views.currencydisplay

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import no.mhl.clarence.R

class CurrencyDisplay(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    // region View Properties
    private val value: TextView by lazy { findViewById<TextView>(R.id.value) }
    private val name: TextView by lazy { findViewById<TextView>(R.id.name) }
    // endregion

    // region Initialisation
    init {
        View.inflate(context, R.layout.view_currency_display, this)
    }
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
            clearValue()
        } else {
            value.text = value.text.substring(0, value.text.length -1)
        }
    }

    fun clearValue() {
        value.text = "0"
    }

    fun setText(text: String) {
        value.text = text
    }

    fun getText() = value.text.toString()

    fun setName(currency: String) { name.text = currency }
    // endregion

}
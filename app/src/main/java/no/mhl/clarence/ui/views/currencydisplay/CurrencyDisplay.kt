package no.mhl.clarence.ui.views.currencydisplay

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import no.mhl.clarence.R
import java.math.BigDecimal

// region Static Constants
private val SECONDARY_DISPLAY_ALPHA: Float = 0.6f
// endregion

class CurrencyDisplay(context: Context, private val attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {

    // region View Properties
    private val value: TextView by lazy { findViewById<TextView>(R.id.value) }
    private val name: TextView by lazy { findViewById<TextView>(R.id.name) }
    // endregion

    // region Properties
    private val valuePlaceholder: String = resources.getString(R.string.currency_display_placeholder_value)
    // endregion

    // region Initialisation
    init {
        View.inflate(context, R.layout.view_currency_display, this)
        setupAttributes()
        setupInitialValue()
    }
    // endregion

    // region View Setup
    private fun setupAttributes() {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CurrencyDisplay,
            0, 0
        ).apply {
            try {
                when (getInt(R.styleable.CurrencyDisplay_displayType, 0)) {
                    1 -> setDisplayAsSecondary()
                }
            } finally {
                recycle()
            }
        }
    }

    private fun setupInitialValue() {
        value.text = valuePlaceholder
    }

    private fun setDisplayAsSecondary() {
        value.alpha = SECONDARY_DISPLAY_ALPHA
        name.alpha = SECONDARY_DISPLAY_ALPHA
    }
    // endregion

    // region Value Text IO
    fun appendValue(value: String) {
        if (this.value.text.toString() == valuePlaceholder) {
            this.value.text = value
        } else {
            this.value.append(value)
        }
    }

    fun backspaceValue() {
        if (value.text.length == 1) {
            clearValue()
        } else {
            value.text = value.text.substring(0, value.text.length - 1)
        }
    }

    fun clearValue() {
        value.text = valuePlaceholder
    }

    fun setText(text: String) {
        value.text = text
    }

    fun getText() = value.text.toString()

    fun setName(currency: String) {
        name.text = currency
    }
    // endregion

}
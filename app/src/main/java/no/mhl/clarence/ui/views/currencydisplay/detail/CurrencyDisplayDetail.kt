package no.mhl.clarence.ui.views.currencydisplay.detail

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import no.mhl.clarence.R

// region Static Constants
private const val SECONDARY_DISPLAY_ALPHA: Float = 0.6f
// endregion

class CurrencyDisplayDetail(context: Context, private val attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {

    // region View Properties
    private val valueText: TextView by lazy { findViewById<TextView>(R.id.value) }
    private val nameText: TextView by lazy { findViewById<TextView>(R.id.name) }
    // endregion

    // region Properties
    private val valuePlaceholder: String = resources.getString(R.string.currency_display_placeholder_value)
    var value: String
        get() = valueText.text.toString()
        set(value) { valueText.text = value }
    var name: String
        get() = nameText.text.toString()
        set(value) { nameText.text = value }
    // endregion

    // region Initialisation
    init {
        View.inflate(context, R.layout.view_currency_display_detail, this)
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

    private fun setupInitialValue() { value = valuePlaceholder }

    private fun setDisplayAsSecondary() { this.alpha = SECONDARY_DISPLAY_ALPHA }
    // endregion

    // region Value Text IO
    fun appendValue(newValue: String) {
        if (value == valuePlaceholder) value = newValue else value += newValue
    }

    fun backspaceValue() {
        if (value.length == 1) clearValue() else value = value.substring(0, value.length -1)
    }

    fun clearValue() { value = valuePlaceholder }
    // endregion

}
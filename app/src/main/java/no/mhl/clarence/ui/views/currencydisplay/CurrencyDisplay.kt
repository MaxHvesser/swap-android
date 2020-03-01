package no.mhl.clarence.ui.views.currencydisplay

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import no.mhl.clarence.R
import no.mhl.clarence.ui.views.currencydisplay.detail.CurrencyDisplayDetail
import java.math.BigDecimal

class CurrencyDisplay(context: Context, private val attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {

    // region View Properties
    val primary: CurrencyDisplayDetail by lazy { findViewById<CurrencyDisplayDetail>(R.id.primary) }
    val secondary: CurrencyDisplayDetail by lazy { findViewById<CurrencyDisplayDetail>(R.id.secondary) }
    // endregion

    // region Initialisation
    init {
        View.inflate(context, R.layout.view_currency_display, this)
    }
    // endregion

    // region Currency Conversion
    fun convertValueAndFormat(rate: BigDecimal) {
        val inputValue: BigDecimal? = primary.value.toBigDecimalOrNull()
        inputValue?.let { value ->
            secondary.value = String.format("%.2f", value * rate)
        }
    }
    // endregion

}
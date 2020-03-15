package no.mhl.clarence.ui.views.currencydisplay

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import no.mhl.clarence.R
import no.mhl.clarence.application.Constants.ANIM_DURATION
import no.mhl.clarence.application.Constants.PRIMARY_DISPLAY_ALPHA
import no.mhl.clarence.application.Constants.SECONDARY_DISPLAY_ALPHA
import no.mhl.clarence.ui.views.currencydisplay.detail.CurrencyDisplayDetail
import no.mhl.clarence.ui.views.keypad.KeypadKey
import no.mhl.clarence.util.consumeKeyForDisplay
import java.math.BigDecimal

class CurrencyDisplay(context: Context, private val attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {

    // region View Properties
    val primary: CurrencyDisplayDetail by lazy { findViewById<CurrencyDisplayDetail>(R.id.primary) }
    val secondary: CurrencyDisplayDetail by lazy { findViewById<CurrencyDisplayDetail>(R.id.secondary) }
    // endregion

    // region Properties
    private var swapped: Boolean = false
    private val focusedDetail: CurrencyDisplayDetail
        get() = if (swapped) secondary else primary
    private val unfocusedDetail: CurrencyDisplayDetail
        get() = if (swapped) primary else secondary
    // endregion

    // region Initialisation
    init {
        View.inflate(context, R.layout.view_currency_display, this)
    }
    // endregion

    // region Keypad Key Consuming
    fun consumeKeyEvent(key: KeypadKey) {
        consumeKeyForDisplay(key, focusedDetail)
        if (key == KeypadKey.BACKSPACE) { consumeKeyForDisplay(key, unfocusedDetail) }
    }
    // endregion

    // region Currency Conversion
    fun convertValueAndFormat(rate: BigDecimal) {
        val inputValue: BigDecimal? = focusedDetail.value.toBigDecimalOrNull()
        inputValue?.let { value ->
            unfocusedDetail.value = String.format("%.2f", value * rate)
        }
    }
    // endregion

    // region Animate Swap
    fun animateSwap() {
        fun swap(view: View, isPrimary: Boolean) {
            ViewCompat
                .animate(view)
                .y(if (isPrimary) secondary.y else primary.y)
                .alpha(if (swapped) {
                    if (isPrimary) PRIMARY_DISPLAY_ALPHA else SECONDARY_DISPLAY_ALPHA
                } else {
                    if (isPrimary) SECONDARY_DISPLAY_ALPHA else PRIMARY_DISPLAY_ALPHA
                })
                .setDuration(ANIM_DURATION)
                .setInterpolator(OvershootInterpolator())
                .start()
        }

        swap(primary, true)
        swap(secondary, false)

        toggleSwapped()
        tidyTrailing()
    }
    // endregion

    // region Misc
    private fun toggleSwapped() { swapped = swapped.not() }

    private fun tidyTrailing() {
            focusedDetail.value = focusedDetail.value.replace(".00", "")
    }
    // endregion

}
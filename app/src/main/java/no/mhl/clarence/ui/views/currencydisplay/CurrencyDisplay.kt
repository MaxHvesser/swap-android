package no.mhl.clarence.ui.views.currencydisplay

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import no.mhl.clarence.R
import no.mhl.clarence.ui.views.currencydisplay.detail.CurrencyDisplayDetail
import no.mhl.clarence.ui.views.keypad.KeypadKey
import no.mhl.clarence.util.consumeKeyForDisplay
import java.math.BigDecimal

// region Static Constants
private const val SECONDARY_DISPLAY_ALPHA: Float = 0.6f
private const val PRIMARY_DISPLAY_ALPHA: Float = 1f
private const val ANIM_DURATION: Long = 250L
// endregion

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
        ViewCompat
            .animate(primary)
            .y(secondary.y)
            .alpha(if (swapped) PRIMARY_DISPLAY_ALPHA else SECONDARY_DISPLAY_ALPHA)
            .setDuration(ANIM_DURATION)
            .setInterpolator(OvershootInterpolator())
            .start()

        ViewCompat
            .animate(secondary)
            .y(primary.y)
            .alpha(if (swapped) SECONDARY_DISPLAY_ALPHA else PRIMARY_DISPLAY_ALPHA)
            .setDuration(ANIM_DURATION)
            .setInterpolator(OvershootInterpolator())
            .start()

        toggleSwapped()
    }
    // endregion

    // region Misc
    private fun toggleSwapped() { swapped = swapped.not() }
    // endregion

}
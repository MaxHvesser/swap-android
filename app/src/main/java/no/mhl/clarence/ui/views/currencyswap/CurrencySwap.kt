package no.mhl.clarence.ui.views.currencyswap

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import no.mhl.clarence.R
import no.mhl.clarence.data.model.Currency
import no.mhl.clarence.ui.views.currencychip.CurrencyChip
import no.mhl.clarence.ui.views.currencychip.SwapChip
import no.mhl.clarence.ui.views.currencydisplay.CurrencyDisplay

class CurrencySwap(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    // region View Properties
    val primary by lazy { findViewById<CurrencyChip>(R.id.primary) }
    val secondary by lazy { findViewById<CurrencyChip>(R.id.secondary) }
    val swap by lazy { findViewById<SwapChip>(R.id.swap) }
    // endregion

    // region Properties
    private var primaryInitialX: Float = 0f
    private var secondaryInitialX: Float = 0f
    // endregion

    // region Initialisation
    init {
        View.inflate(context, R.layout.view_currency_swap, this)

        setupSwapChip()
    }
    // endregion

    // region Currency Chip Setup
    fun setupPrimaryChipForCurrency(currency: Currency) {
        primary.setupChipForCurrency(currency, true)
    }

    fun setupSecondaryChipForCurrency(currency: Currency) {
        secondary.setupChipForCurrency(currency)
    }
    // endregion

    // region Swap Chip Setup
    private fun setupSwapChip() {
        swap.setOnClickListener {
            primaryInitialX = primary.x
            secondaryInitialX = secondary.x
            swapChips()
        }
    }
    // endregion

    // region Swap Animation
    private fun swapChips() {
        ViewCompat
            .animate(primary)
            .x(secondaryInitialX)
            .setDuration(250)
            .setInterpolator(OvershootInterpolator())
            .start()

        ViewCompat
            .animate(secondary)
            .x(primaryInitialX)
            .setDuration(250)
            .setInterpolator(OvershootInterpolator())
            .start()
    }
    // endregion

}
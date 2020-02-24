package no.mhl.clarence.ui.views.currencyswap

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import no.mhl.clarence.R
import no.mhl.clarence.data.model.Currency
import no.mhl.clarence.ui.views.currencychip.CurrencyChip
import no.mhl.clarence.ui.views.currencychip.SwapChip

class CurrencySwap(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    // region View Properties
    val primary by lazy { findViewById<CurrencyChip>(R.id.primary) }
    val secondary by lazy { findViewById<CurrencyChip>(R.id.secondary) }
    val swap by lazy { findViewById<SwapChip>(R.id.swap) }
    // endregion

    // region Initialisation
    init {
        View.inflate(context, R.layout.view_currency_swap, this)
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

}
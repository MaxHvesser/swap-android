package no.mhl.clarence.ui.views.currencyswap

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.OvershootInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.lifecycle.MutableLiveData
import no.mhl.clarence.R
import no.mhl.clarence.data.model.Currency
import no.mhl.clarence.ui.views.currencychip.CurrencyChip
import no.mhl.clarence.ui.views.currencychip.SwapChip
import no.mhl.clarence.ui.views.currencydisplay.CurrencyDisplay

class CurrencySwap(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    // region View Properties
    val primary: CurrencyChip by lazy { findViewById<CurrencyChip>(R.id.primary) }
    val secondary: CurrencyChip by lazy { findViewById<CurrencyChip>(R.id.secondary) }
    val swap: SwapChip by lazy { findViewById<SwapChip>(R.id.swap) }
    // endregion

    // region Properties
    private var primaryInitialX: Float = 0f
    private var secondaryInitialX: Float = 0f
    // endregion

    // region Click Exposure
    val swapClickEvent: MutableLiveData<Boolean> = MutableLiveData()
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
            swapClickEvent.postValue(true)
        }
    }
    // endregion

    // region Swap Animation
    private fun swapChips() {
        fun animateSwapForView(view: View, isPrimary: Boolean) {
            ViewCompat
                .animate(view)
                .x(if (isPrimary) secondaryInitialX else primaryInitialX)
                .setInterpolator(OvershootInterpolator())
                .withStartAction { swap.isClickable = false }
                .withEndAction { swap.isClickable = true }
                .start()
        }
        animateSwapForView(primary, true)
        animateSwapForView(secondary, false)
    }
    // endregion

}
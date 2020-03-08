package no.mhl.clarence.ui.views.currencychip

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.fragment_currency_selection.view.*
import kotlinx.android.synthetic.main.view_currency_display.view.*
import no.mhl.clarence.R
import no.mhl.clarence.data.model.Currency
import no.mhl.clarence.util.currencyAsDrawable

class CurrencyChip(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    // region View Properties
    private val name by lazy { findViewById<TextView>(R.id.chip_text) }
    private val icon by lazy { findViewById<ImageView>(R.id.chip_icon) }
    private val parent by lazy { findViewById<ConstraintLayout>(R.id.parent) }
    // endregion

    // region Initialisation
    init {
        View.inflate(context, R.layout.view_chip_currency, this)
    }
    // endregion

    // region View Setup
    fun setupChipForCurrency(currency: Currency, isBase: Boolean = false) {
        name.text = currency.name
        icon.setImageResource(currencyAsDrawable(currency.name))
        parent.setBackgroundResource(when (isBase) {
            true -> R.drawable.ripple_chip_primary
            false -> R.drawable.ripple_chip_secondary
        })
    }
    // endregion

}
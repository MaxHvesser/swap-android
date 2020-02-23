package no.mhl.clarence.ui.views.currencychip

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import no.mhl.clarence.R

class CurrencyChip(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    // region View Properties
    private val name by lazy { findViewById<TextView>(R.id.chip_text) }
    private val icon by lazy { findViewById<ImageView>(R.id.chip_icon) }
    private val parent by lazy { findViewById<ConstraintLayout>(R.id.parent) }
    // endregion

    // region Initialisation
    init {
        View.inflate(context, R.layout.view_chip_currency, this)

        // Testing
        name.text = "EUR"
        icon.setImageResource(R.drawable.ic_eur)
        parent.setBackgroundResource(R.drawable.background_chip_currency_primary)
    }
    // endregion

}
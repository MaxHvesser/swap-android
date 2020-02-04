package no.mhl.clarence.ui.views.currencydisplay

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import no.mhl.clarence.R

class CurrencyDisplay(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    // region View Properties
    private val value: TextView by lazy { findViewById<TextView>(R.id.currency_display_value) }
    private val nameContainer: LinearLayout by lazy { findViewById<LinearLayout>(R.id.currency_display_name_container) }
    private val name: TextView by lazy { findViewById<TextView>(R.id.currency_display_name) }
    private val flag: ImageView by lazy { findViewById<ImageView>(R.id.currency_display_flag) }
    // endregion

    // region Click Exposure
    val currencySelectionClick: MutableLiveData<String> = MutableLiveData()
    // endregion

    // region Initialisation
    init {
        View.inflate(context, R.layout.view_currency_display, this)
        setupViews()
    }
    // endregion

    // region Setup Views
    private fun setupViews() {
        setupCurrencySelection()
    }

    private fun setupCurrencySelection() {
        nameContainer.setOnClickListener { currencySelectionClick.postValue("ok") }
    }
    // endregion

    // region Value Text IO
    fun appendValue(value: String) {
        if (this.value.text.toString() == "0") {
            this.value.text = value
        } else {
            this.value.append(value)
        }
    }

    fun backspaceValue() {
        if (value.text.length == 1) {
            clearValue()
        } else {
            value.text = value.text.substring(0, value.text.length -1)
        }
    }

    fun clearValue() {
        value.text = "0"
    }

    fun setText(text: String) {
        value.text = text
    }

    fun getText() = value.text.toString()

    fun setName(currency: String) { name.text = currency }

    fun setFlagResource(resource: Int) { flag.setImageResource(resource) }
    // endregion

}
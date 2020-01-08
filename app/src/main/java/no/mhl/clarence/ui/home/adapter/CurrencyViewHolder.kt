package no.mhl.clarence.ui.home.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import no.mhl.clarence.R

class CurrencyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // region View Properties
    private val currency = view.findViewById<TextView>(R.id.row_currency_name)
    private val currencyValue = view.findViewById<TextView>(R.id.row_currency_value)
    private val flag = view.findViewById<ImageView>(R.id.row_currency_flag)
    // endregion

    // region Bind Data
    fun bind(item: Int) {
        currency.text = "NOK"
        currencyValue.text = item.toString()
    }
    // endregion

}
package no.mhl.swap.ui.currencyselection.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import no.mhl.swap.R
import no.mhl.swap.data.model.Currency
import no.mhl.swap.util.currencyAsDrawable

class CurrencyItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // region View Properties
    private val name: TextView = view.findViewById(R.id.row_currency_name)
    private val fullName: TextView = view.findViewById(R.id.row_currency_full_name)
    private val flag: ImageView = view.findViewById(R.id.row_currency_flag)
    // endregion

    // region View Details Setup
    fun bind(currency: Currency, currencyClickEvent: MutableLiveData<Currency>) {
        setupRowDetails(currency)
        itemView.setOnClickListener { currencyClickEvent.postValue(currency) }
    }

    private fun setupRowDetails(currency: Currency) {
        name.text = currency.name
        fullName.text = currency.fullName
        flag.setImageResource(currencyAsDrawable(currency.name))
    }
    // endregion

}
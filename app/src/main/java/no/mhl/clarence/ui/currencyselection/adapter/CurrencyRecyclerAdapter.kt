package no.mhl.clarence.ui.currencyselection.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import no.mhl.clarence.R
import no.mhl.clarence.data.model.Currency
import no.mhl.clarence.ui.currencyselection.adapter.viewholder.CurrencyItemViewHolder

class CurrencyRecyclerAdapter(private val items: List<Currency>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    // region Filter
    val filteredCurrencies: MutableList<Currency> = mutableListOf()
    private val currencyFilter = CurrencyFilter(this, items)
    // endregion

    // region Initialisation
    init {
        filteredCurrencies.addAll(items)
    }
    // endregion

    // region Holder Creation
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CurrencyItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_item_currency,
                parent,
                false
            )
        )
    // endregion

    // region Binding
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CurrencyItemViewHolder).bind(filteredCurrencies[position])
    }
    // endregion

    // region Misc
    override fun getItemCount() = filteredCurrencies.count()

    override fun getFilter() = currencyFilter
    // endregion

}
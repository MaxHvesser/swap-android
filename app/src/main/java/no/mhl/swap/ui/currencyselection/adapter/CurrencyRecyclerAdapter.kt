package no.mhl.swap.ui.currencyselection.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import no.mhl.swap.R
import no.mhl.swap.data.model.Currency
import no.mhl.swap.ui.currencyselection.adapter.viewholder.CurrencyItemViewHolder

class CurrencyRecyclerAdapter(items: List<Currency>) :
    RecyclerView.Adapter<CurrencyItemViewHolder>(), Filterable {

    // region Filter
    val filteredCurrencies: MutableList<Currency> = mutableListOf()
    private val currencyFilter = CurrencyFilter(this, items)
    // endregion

    // region Click Exposure
    val currencyClickEvent: MutableLiveData<Currency> = MutableLiveData()
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
    override fun onBindViewHolder(holder: CurrencyItemViewHolder, position: Int) {
        holder.bind(filteredCurrencies[position], currencyClickEvent)
    }
    // endregion

    // region Misc
    override fun getItemCount() = filteredCurrencies.count()

    override fun getFilter() = currencyFilter
    // endregion

}
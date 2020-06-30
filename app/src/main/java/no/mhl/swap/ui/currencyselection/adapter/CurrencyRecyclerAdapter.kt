package no.mhl.swap.ui.currencyselection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filterable
import androidx.annotation.LayoutRes
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import no.mhl.swap.R
import no.mhl.swap.data.model.Currency
import no.mhl.swap.data.model.view.CURRENCY
import no.mhl.swap.data.model.view.CurrencyListItem
import no.mhl.swap.data.model.view.HEADER
import no.mhl.swap.data.model.view.Header
import no.mhl.swap.ui.currencyselection.adapter.viewholder.CurrencyItemViewHolder
import no.mhl.swap.ui.currencyselection.adapter.viewholder.HeaderItemViewHolder
import java.lang.IllegalArgumentException

class CurrencyRecyclerAdapter(
    val items: List<CurrencyListItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    // region Filter
    val filteredCurrencies: MutableList<CurrencyListItem> = mutableListOf()
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        fun inflate(@LayoutRes layout: Int) =
            LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return when (viewType) {
            HEADER -> HeaderItemViewHolder(inflate(R.layout.row_item_header_currency))
            CURRENCY -> CurrencyItemViewHolder(inflate(R.layout.row_item_currency))
            else -> throw IllegalArgumentException()
        }
    }
    // endregion

    // region Binding
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            HEADER -> (holder as HeaderItemViewHolder).bind(items[position] as Header)
            CURRENCY -> (holder as CurrencyItemViewHolder).bind(
                items[position] as Currency,
                currencyClickEvent,
                items[position - 1],
                if (position + 1 <= items.lastIndex) items[position + 1] else null
            )
        }
    }
    // endregion

    // region Misc
    override fun getItemCount() = filteredCurrencies.count()

    override fun getItemViewType(position: Int) = items[position].type

    override fun getFilter() = currencyFilter
    // endregion

}
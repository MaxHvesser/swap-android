package no.mhl.swap.ui.currencyselection.adapter

import android.widget.Filter
import no.mhl.swap.data.model.Currency
import java.util.*


class CurrencyFilter(
    private val adapter: CurrencyRecyclerAdapter,
    private val currencyItems: List<Currency>
) : Filter() {

    // region Properties
    private val filteredCurrencies: MutableList<Currency> = mutableListOf()
    // endregion

    // region Filtering
    override fun performFiltering(constraint: CharSequence?): FilterResults {
        filteredCurrencies.clear()

        val results = FilterResults()

        filteredCurrencies.addAll(currencyItems.filter { currency ->
            val searchTerm = constraint.toString().toLowerCase(Locale.getDefault()).trim()
            val name = currency.name.toLowerCase(Locale.getDefault()).trim()
            val fullName = currency.fullName.toLowerCase(Locale.getDefault()).trim()

            return@filter when {
                name.contains(searchTerm) -> true
                fullName.contains(searchTerm) -> true
                else -> false
            }
        })

        results.apply {
            values = filteredCurrencies
            count = filteredCurrencies.count()
        }

        return results
    }
    // endregion

    // region Results
    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        adapter.filteredCurrencies.clear()
        adapter.filteredCurrencies.addAll(filteredCurrencies)
        adapter.notifyDataSetChanged()
    }
    // endregion

}
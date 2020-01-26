package no.mhl.clarence.ui.currencyselection.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import no.mhl.clarence.R
import no.mhl.clarence.data.model.Currency
import no.mhl.clarence.ui.currencyselection.adapter.viewholder.CurrencyItemViewHolder

class CurrencyRecyclerAdapter(private val items: List<Currency>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        (holder as CurrencyItemViewHolder).bind(items[position])
    }
    // endregion

    // region Misc
    override fun getItemCount() = items.count()
    // endregion

}
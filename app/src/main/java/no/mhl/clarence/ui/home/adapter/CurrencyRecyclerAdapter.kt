package no.mhl.clarence.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import no.mhl.clarence.R

class CurrencyRecyclerAdapter(private val items: List<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // region Holder Creation
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CurrencyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item_currency, parent, false))
    // endregion

    // region Holder Binding
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        (holder as CurrencyViewHolder).bind(item)
    }
    // endregion

    // region Item Count
    override fun getItemCount() = items.size
    // endregion

}
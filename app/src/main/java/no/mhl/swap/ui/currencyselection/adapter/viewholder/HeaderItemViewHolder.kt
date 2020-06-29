package no.mhl.swap.ui.currencyselection.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import no.mhl.swap.R
import no.mhl.swap.data.model.view.Header
import no.mhl.swap.util.extension.capitalize

class HeaderItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // region View Properties
    private val title: TextView = view.findViewById(R.id.title)
    // endregion

    // region View Setup
    fun bind(header: Header) {
        setupView(header.title)
    }

    private fun setupView(titleResource: String) {
        title.text = titleResource.capitalize()
    }
    // endregion

}
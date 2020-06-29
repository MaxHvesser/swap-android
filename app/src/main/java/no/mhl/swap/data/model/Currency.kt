package no.mhl.swap.data.model

import no.mhl.swap.data.model.view.CURRENCY
import no.mhl.swap.data.model.view.CurrencyListItem

data class Currency(
    val name: String,
    val fullName: String
) : CurrencyListItem {

    // region Type
    override val type = CURRENCY
    // endregion

}
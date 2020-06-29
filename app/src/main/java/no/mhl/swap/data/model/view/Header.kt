package no.mhl.swap.data.model.view

data class Header(
    val title: String
) : CurrencyListItem {

    // region Type
    override val type = HEADER
    // endregion

}
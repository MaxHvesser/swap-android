package no.mhl.swap.util

import no.mhl.swap.R
import no.mhl.swap.data.model.Continent
import no.mhl.swap.data.model.Currency

// region Currency List Generation
/***
 * Fetch a complete list of currencies for each continent of the world.
 * @return a List of Continent
 */
fun currencyList(): List<Continent> {
    return listOf(
        africa(),
        asia(),
        australia(),
        europe(),
        northAmerica(),
        southAmerica()
    )
}

private fun africa() = Continent(
    CONT_AFRICA,
    listOf(
        Currency("ZAR", "South African Rand")
    )
)

private fun asia() = Continent(
    CONT_ASIA,
    listOf(
        Currency("CNY", "Chinese Yuan"),
        Currency("HKD", "Hong Kong Dollars"),
        Currency("IDR", "Indonesian Rupiajh"),
        Currency("ILS", "Israeli New Shekel"),
        Currency("INR", "Indian Rupee"),
        Currency("JPY", "Japanese Yen"),
        Currency("KRW", "South Korean Won"),
        Currency("MYR", "Malaysian Ringgit"),
        Currency("PHP", "Philippine Peso"),
        Currency("SGD", "Singapore Dollar"),
        Currency("THB", "Thai Baht"),
        Currency("TRY", "Turkish Lira")
    )
)

private fun australia() = Continent(
    CONT_AUSTRALIA,
    listOf(
        Currency("AUD", "Australian Dollars"),
        Currency("NZD", "New Zealand Dollars")
    )
)

private fun europe() = Continent(
    CONT_EUROPE,
    listOf(
        Currency("BGN", "Bulgarian Lev"),
        Currency("CHF", "Swiss Francs"),
        Currency("CNY", "Chinese Yuan"),
        Currency("CZK", "Czech Koruna"),
        Currency("DKK", "Danish Kroner"),
        Currency("EUR", "Euros"),
        Currency("GBP", "Great British Pounds"),
        Currency("HRK", "Croatian Kuna"),
        Currency("HUF", "Hungarian Forint"),
        Currency("ISK", "Icelandic Kroner"),
        Currency("NOK", "Norwegian Kroner"),
        Currency("NZD", "New Zealand Dollars"),
        Currency("PLN", "Poland Zloty"),
        Currency("RON", "Romanian Leu"),
        Currency("RUB", "Russian Ruble"),
        Currency("SEK", "Swedish Kroner")
    )
)

private fun northAmerica() = Continent(
    CONT_NORTH_AMERICA,
    listOf(
        Currency("USD", "United States Dollars"),
        Currency("CAD", "Canadian Dollars")
    )
)

private fun southAmerica() = Continent(
    CONT_SOUTH_AMERICA,
    listOf(
        Currency("BRL", "Brazilian Real"),
        Currency("MXN", "Mexican Peso")
    )
)
// endregion

// region Currency As Drawable
fun currencyAsDrawable(name: String): Int {
    return when (name) {
        "AUD" -> R.drawable.ic_aud
        "BGN" -> R.drawable.ic_bgn
        "BRL" -> R.drawable.ic_brl
        "CAD" -> R.drawable.ic_cad
        "CHF" -> R.drawable.ic_chf
        "CNY" -> R.drawable.ic_cny
        "CZK" -> R.drawable.ic_czk
        "DKK" -> R.drawable.ic_dkk
        "EUR" -> R.drawable.ic_eur
        "GBP" -> R.drawable.ic_gbp
        "HKD" -> R.drawable.ic_hkd
        "HRK" -> R.drawable.ic_hrk
        "HUF" -> R.drawable.ic_huf
        "IDR" -> R.drawable.ic_idr
        "ILS" -> R.drawable.ic_ils
        "INR" -> R.drawable.ic_inr
        "ISK" -> R.drawable.ic_isk
        "JPY" -> R.drawable.ic_jpy
        "KRW" -> R.drawable.ic_krw
        "MXN" -> R.drawable.ic_mxn
        "MYR" -> R.drawable.ic_myr
        "NOK" -> R.drawable.ic_nok
        "NZD" -> R.drawable.ic_nzd
        "PHP" -> R.drawable.ic_php
        "PLN" -> R.drawable.ic_pln
        "RON" -> R.drawable.ic_ron
        "RUB" -> R.drawable.ic_rub
        "SEK" -> R.drawable.ic_sek
        "SGD" -> R.drawable.ic_sgd
        "THB" -> R.drawable.ic_thb
        "TRY" -> R.drawable.ic_try
        "USD" -> R.drawable.ic_usd
        "ZAR" -> R.drawable.ic_zar
        else -> R.drawable.ic_eur
    }
}
// endregion

// region Static Constant Values
private const val CONT_AFRICA = "africa"
private const val CONT_ASIA = "asia"
private const val CONT_AUSTRALIA = "australia"
private const val CONT_EUROPE = "europe"
private const val CONT_NORTH_AMERICA = "north america"
private const val CONT_SOUTH_AMERICA = "south america"
// endregion
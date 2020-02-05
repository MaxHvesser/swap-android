package no.mhl.clarence.util

import no.mhl.clarence.R
import no.mhl.clarence.data.model.Currency

fun generateCurrencyList(): List<Currency> = listOf(
    Currency("AUD", "Australian Dollars"),
    Currency("BGN", "Bulgarian Lev"),
    Currency("BRL", "Brazilian Real"),
    Currency("CAD", "Canadian Dollars"),
    Currency("CHF", "Swiss Francs"),
    Currency("CNY", "Chinese Yuan"),
    Currency("CZK", "Czech Koruna"),
    Currency("DKK", "Danish Kroner"),
    Currency("EUR", "Euros"),
    Currency("GBP", "Great British Pounds"),
    Currency("HKD", "Hong Kong Dollars"),
    Currency("HRK", "Croatian Kuna"),
    Currency("HUF", "Hungarian Forint"),
    Currency("IDR", "Indonesian Rupiajh"),
    Currency("ILS", "Israeli New Shekel"),
    Currency("INR", "Indian Rupee"),
    Currency("ISK", "Icelandic Kroner"),
    Currency("JPY", "Japanese Yen"),
    Currency("KRW", "South Korean Won"),
    Currency("MXN", "Mexican Peso"),
    Currency("MYR", "Malaysian Ringgit"),
    Currency("NOK", "Norwegian Kroner"),
    Currency("NZD", "New Zealand Dollars"),
    Currency("PHP", "Philippine Peso"),
    Currency("PLN", "Poland Zloty"),
    Currency("RON", "Romanian Leu"),
    Currency("RUB", "Russian Ruble"),
    Currency("SEK", "Swedish Kroner"),
    Currency("SGD", "Singapore Dollar"),
    Currency("THB", "Thai Baht"),
    Currency("TRY", "Turkish Lira"),
    Currency("USD", "United States Dollars"),
    Currency("ZAR", "South African Rand")
)

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
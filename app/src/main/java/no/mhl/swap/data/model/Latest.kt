package no.mhl.swap.data.model

import java.math.BigDecimal

data class Latest(
    val rates: Map<String, BigDecimal>,
    val base: String,
    val date: String
)

// region Helper
fun mapToRate(latest: Latest): Rate =
    Rate(0, latest.base, latest.date, mapToCurrencyValues(latest.rates))

private fun mapToCurrencyValues(map: Map<String, BigDecimal>): List<CurrencyValue> =
    map.map { CurrencyValue(it.key, it.value) }
// endregion
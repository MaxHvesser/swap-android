package no.mhl.clarence.data.model

data class Latest(
    val rates: Map<String, Double>,
    val base: String,
    val date: String
)

// region Helper
fun mapToRate(latest: Latest): Rate =
    Rate(0, latest.base, latest.date, mapToCurrencyValues(latest.rates))

private fun mapToCurrencyValues(map: Map<String, Double>): List<CurrencyValue> =
    map.map { CurrencyValue(it.key, it.value) }
// endregion
package no.mhl.clarence.repository

import no.mhl.clarence.data.local.dao.LatestRatesDao
import no.mhl.clarence.data.model.Latest
import no.mhl.clarence.data.remote.ExchangeRatesService

class ExchangeRatesRepository(
    private val exchangeRatesService: ExchangeRatesService,
    private val latestRatesDao: LatestRatesDao
) {

    // region Fetch latest exchange rates from service
    suspend fun fetchLatestExchangeRates() =
        exchangeRatesService.fetchLatestExchangeRates()

    suspend fun fetchLatestExchangeRatesForBase(base: String) =
        exchangeRatesService.fetchLatestExchangeRatesForBase(base)
    // endregion

    // region Database IO
    fun storeLatestRatesLocally(latest: Latest) {
        latestRatesDao.dropAllLatest()
        latestRatesDao.addLatestRates(latest)
    }

    fun storeAllLatestRates(latestRates: List<Latest>) {
        latestRatesDao.dropAllLatest()
        latestRatesDao.addAllLatestRates(latestRates)
    }

    fun fetchLocalLatestRates() =
        latestRatesDao.fetchLatestRates()

    fun fetchLatestForBase(base: String) =
        latestRatesDao.fetchLatestForBase(base)
    // endregion

}
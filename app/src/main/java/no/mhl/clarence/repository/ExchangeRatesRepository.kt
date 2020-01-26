package no.mhl.clarence.repository

import no.mhl.clarence.data.local.dao.LatestRatesDao
import no.mhl.clarence.data.model.Latest
import no.mhl.clarence.data.remote.ExchangeRatesService

class ExchangeRatesRepository(
    private val exchangeRatesService: ExchangeRatesService,
    private val latestRatesDao: LatestRatesDao
) {

    // region Fetch latest exchange rates from service
    suspend fun fetchLatestExchangeRates() = exchangeRatesService.fetchLatestExchangeRates()
    // endregion

    // region Database IO
    fun storeLatestRatesLocally(latest: Latest) =
        latestRatesDao.addLatestRates(latest)

    fun fetchLocalLatestRates() =
        latestRatesDao.fetchLatestRates()
    // endregion

}
package no.mhl.clarence.repository

import no.mhl.clarence.data.local.dao.RateDao
import no.mhl.clarence.data.model.Rate
import no.mhl.clarence.data.remote.ExchangeRatesService

class ExchangeRatesRepository(
    private val exchangeRatesService: ExchangeRatesService,
    private val rateDao: RateDao
) {

    // region Fetch latest exchange rates from service
    suspend fun fetchLatestExchangeRates() =
        exchangeRatesService.fetchLatestExchangeRates()

    suspend fun fetchLatestExchangeRatesForBase(base: String) =
        exchangeRatesService.fetchLatestExchangeRatesForBase(base)
    // endregion

    // region Database IO
    fun storeRateInDb(rate: Rate) =
        rateDao.addRate(rate)

    fun storeAllRatesInDb(rates: List<Rate>) =
        rateDao.addAllRates(rates)

    fun fetchAllRatesFromDb() =
        rateDao.fetchRates()

    fun fetchRateForBaseFromDb(base: String) =
        rateDao.fetchRateForBase(base)

    fun deleteRatesFromDb() =
        rateDao.dropAllRates()
    // endregion

}
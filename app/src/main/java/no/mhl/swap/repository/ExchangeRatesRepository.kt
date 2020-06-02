package no.mhl.swap.repository

import no.mhl.swap.data.local.dao.ExchangeDao
import no.mhl.swap.data.local.dao.RateDao
import no.mhl.swap.data.model.Exchange
import no.mhl.swap.data.model.Rate
import no.mhl.swap.data.remote.ExchangeRatesService

class ExchangeRatesRepository(
    private val exchangeRatesService: ExchangeRatesService,
    private val rateDao: RateDao,
    private val exchangeDao: ExchangeDao
) {

    // region Fetch latest exchange rates from service
    suspend fun fetchLatestExchangeRatesForBase(base: String) =
        exchangeRatesService.fetchLatestExchangeRatesForBase(base)
    // endregion

    // region Database IO
    fun clearRatesAndStore(rates: List<Rate>) {
        rateDao.dropAllRates()
        rateDao.addAllRates(rates)
    }

    fun fetchRateForBaseFromDb(base: String) =
        rateDao.fetchRateForBase(base)

    fun storeExchangeInDb(exchange: Exchange) =
        exchangeDao.addExchange(exchange)

    fun fetchExchangeFromDb() =
        exchangeDao.fetchExchange()

    private fun deleteExchangeFromDb() =
        exchangeDao.dropExchange()

    fun exchangeCount() =
        exchangeDao.count()

    fun updateExchange(exchange: Exchange) =
        exchangeDao.updateExchange(exchange)

    fun replaceExchange(exchange: Exchange) {
        deleteExchangeFromDb()
        storeExchangeInDb(exchange)
    }
    // endregion

}
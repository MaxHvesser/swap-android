package no.mhl.swap.data.remote

import no.mhl.swap.application.Constants
import no.mhl.swap.application.Constants.EXCHANGE_RATES_BASE_QUERY
import no.mhl.swap.data.model.Latest
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRatesService {

    // region Fetch Latest Rates
    @GET(Constants.EXCHANGE_RATES_LATEST)
    suspend fun fetchLatestExchangeRates(): Latest

    @GET(Constants.EXCHANGE_RATES_LATEST)
    suspend fun fetchLatestExchangeRatesForBase(@Query(EXCHANGE_RATES_BASE_QUERY) base: String) : Latest
    // endregion

}
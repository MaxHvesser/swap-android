package no.mhl.clarence.data.remote

import no.mhl.clarence.application.Constants
import no.mhl.clarence.data.model.Latest
import retrofit2.Response
import retrofit2.http.GET

interface ExchangeRatesService {

    // region Fetch Latest Rates
    @GET(Constants.EXCHANGE_RATES_LATEST)
    suspend fun fetchLatestExchangeRates() : Response<Latest>
    // endregion

}
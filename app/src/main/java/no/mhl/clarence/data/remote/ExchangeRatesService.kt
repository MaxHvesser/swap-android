package no.mhl.clarence.data.remote

import androidx.lifecycle.LiveData
import com.google.gson.JsonObject
import no.mhl.clarence.application.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ExchangeRatesService {

    // region Fetch Latest Rates
    @GET(Constants.EXCHANGE_RATES_LATEST)
    suspend fun fetchLatestExchangeRates() : Response<JsonObject>
    // endregion

}
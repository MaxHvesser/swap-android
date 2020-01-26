package no.mhl.clarence.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import no.mhl.clarence.data.model.Latest
import no.mhl.clarence.data.remote.ExchangeRatesService
import no.mhl.clarence.data.remote.common.Resource
import no.mhl.clarence.data.remote.common.Status
import java.lang.Exception

class ExchangeRatesRepository(
    private val exchangeRatesService: ExchangeRatesService
) {

    // region Fetch latest exchange rates from service
    suspend fun fetchLatestExchangeRates(): LiveData<Resource<Latest>> {
        val data = MutableLiveData<Resource<Latest>>()

        try {
            val response = exchangeRatesService.fetchLatestExchangeRates()
            data.value = when (response.isSuccessful) {
                true -> Resource(Status.SUCCESS, response.body(), null)
                false -> Resource(Status.ERROR, null, response.errorBody().toString())
            }
        } catch (exception: Exception) {
            data.value = Resource(Status.ERROR, null, exception.message)
        }

        return data

    }
    // endregion

}
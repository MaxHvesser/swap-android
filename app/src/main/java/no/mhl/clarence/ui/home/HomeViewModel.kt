package no.mhl.clarence.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import no.mhl.clarence.data.remote.common.Resource
import no.mhl.clarence.repository.ExchangeRatesRepository

class HomeViewModel(
    private val exchangeRatesRepository: ExchangeRatesRepository
) : ViewModel() {

    // region Get Latest Rates
    suspend fun downloadLatestExchangeRates(): LiveData<Resource<JsonObject>> =
        exchangeRatesRepository.fetchLatestExchangeRates()
    // endregion

}
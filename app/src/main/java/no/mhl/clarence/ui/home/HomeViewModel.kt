package no.mhl.clarence.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import no.mhl.clarence.data.remote.common.Resource
import no.mhl.clarence.data.remote.common.Status
import no.mhl.clarence.repository.ExchangeRatesRepository

class HomeViewModel(
    private val exchangeRatesRepository: ExchangeRatesRepository
) : ViewModel() {

    // region Get Latest Rates
    fun downloadLatestExchangeRates() = liveData(Dispatchers.IO) {
        val data = try {
            val latest = exchangeRatesRepository.fetchLatestExchangeRates()
            Resource(Status.SUCCESS, latest, null)
        } catch (exception: Exception) {
            Resource(Status.ERROR, null, exception.message)
        }
        emit(data)
    }
    // endregion

    // region Locally Store Rates
    fun refreshRates() = liveData(Dispatchers.IO) {
        val latest = exchangeRatesRepository.fetchLatestExchangeRates()
        exchangeRatesRepository.storeLatestRatesLocally(latest)
        emit(Unit)
    }
    // endregion

}
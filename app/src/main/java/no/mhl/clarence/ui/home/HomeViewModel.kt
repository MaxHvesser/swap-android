package no.mhl.clarence.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.mhl.clarence.data.model.Latest
import no.mhl.clarence.data.remote.common.Resource
import no.mhl.clarence.data.remote.common.Status
import no.mhl.clarence.repository.ExchangeRatesRepository
import java.lang.Exception

class HomeViewModel(
    private val exchangeRatesRepository: ExchangeRatesRepository
) : ViewModel() {

    // region Get Latest Rates
    val downloadLatestExchangeRates = liveData(Dispatchers.IO) {
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
    fun storeLatestRates(latest: Latest?) = CoroutineScope(Dispatchers.IO).launch {
        latest?.let { exchangeRatesRepository.storeLatestRatesLocally(it) }
    }
    // endregion

}
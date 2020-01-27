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
import no.mhl.clarence.util.generateCurrencyList
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

    fun downloadLatestExchangeRates() = liveData(Dispatchers.IO) {
        val currencies = generateCurrencyList()
        val latestRates: MutableList<Latest> = mutableListOf()

        currencies.forEach {  currency ->
            try {
                val latest = exchangeRatesRepository.fetchLatestExchangeRatesForBase(currency.name)
                latestRates.add(latest)
            } catch (exception: Exception) {
                // TODO Log innit
                val t = exception
            }
        }

        storeAllLatestRates(latestRates)
        emit(latestRates)
    }
    // endregion

    // region Locally Store Rates
    fun storeLatestRates(latest: Latest?) = CoroutineScope(Dispatchers.IO).launch {
        latest?.let { exchangeRatesRepository.storeLatestRatesLocally(it) }
    }

    fun storeAllLatestRates(latestRates: List<Latest>) = CoroutineScope(Dispatchers.IO).launch {
        exchangeRatesRepository.storeAllLatestRates(latestRates)
    }

    fun fetchLatestRatesForBase(base: String) = liveData(Dispatchers.IO) {
        emit(exchangeRatesRepository.fetchLatestExchangeRatesForBase(base))
    }
    // endregion

}
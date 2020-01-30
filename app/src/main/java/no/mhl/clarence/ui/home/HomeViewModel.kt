package no.mhl.clarence.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.mhl.clarence.data.local.converters.Converters
import no.mhl.clarence.data.model.Rate
import no.mhl.clarence.data.model.defaultExchange
import no.mhl.clarence.data.model.mapToRate
import no.mhl.clarence.repository.ExchangeRatesRepository
import no.mhl.clarence.util.generateCurrencyList
import java.lang.Exception

class HomeViewModel(
    private val exchangeRatesRepository: ExchangeRatesRepository
) : ViewModel() {

    init {
        initNetworkRequest()
    }

    // region Get Latest Rates
    fun downloadLatestExchangeRates() = liveData(Dispatchers.IO) {
        val rates: MutableList<Rate> = mutableListOf()

        generateCurrencyList().forEach { currency ->
            try {
                val latest = exchangeRatesRepository.fetchLatestExchangeRatesForBase(currency.name)
                rates.add(mapToRate(latest))
            } catch (exception: Exception) {
                Log.e("DownloadRates -> ", "Exception: ", exception)
            }
        }

        storeAllRates(rates)
        emit(rates)
    }
    // endregion


    private fun initNetworkRequest() = CoroutineScope(Dispatchers.IO).launch {
        val rates: MutableList<Rate> = mutableListOf()

        generateCurrencyList().forEach { currency ->
            try {
                val latest = exchangeRatesRepository.fetchLatestExchangeRatesForBase(currency.name)
                rates.add(mapToRate(latest))
            } catch (exception: Exception) {
                Log.e("DownloadRates -> ", "Exception: ", exception)
            }
        }

        storeAllRates(rates)
    }

    // region Locally Store Rates
    private fun storeAllRates(rates: List<Rate>) = CoroutineScope(Dispatchers.IO).launch {
        exchangeRatesRepository.deleteRatesFromDb()
        exchangeRatesRepository.storeAllRatesInDb(rates)
    }
    // endregion

    // region Store and fetch exchange
    fun fetchCurrentExchange() = liveData(Dispatchers.IO) {
        emit(exchangeRatesRepository.fetchExchangeFromDb())
    }

    fun storeDefaultExchange() = CoroutineScope(Dispatchers.IO).launch {
        exchangeRatesRepository.storeExchangeInDb(defaultExchange())
    }
    // endregion

}
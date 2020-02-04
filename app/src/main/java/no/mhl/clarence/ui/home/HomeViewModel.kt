package no.mhl.clarence.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
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

    // region Initialisation
    init {
        //downloadLatestExchangeRates()
    }
    // endregion

    // region Download Status
    val ratesDownloading = MutableLiveData<Int>()
    // endregion

    // region Get Latest Rates
    private fun downloadLatestExchangeRates() = CoroutineScope(Dispatchers.IO).launch {
        ratesDownloading.postValue(0)
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
    // endregion

    // region Locally Store Rates
    private fun storeAllRates(rates: List<Rate>) = CoroutineScope(Dispatchers.IO).launch {
        when (exchangeRatesRepository.ratesCount()) {
            0 -> exchangeRatesRepository.storeAllRatesInDb(rates)
            else -> exchangeRatesRepository.updateRates(rates)
        }
        ratesDownloading.postValue(1)
    }
    // endregion

    // region Store and fetch exchange
    fun fetchCurrentExchange() = liveData(Dispatchers.IO) {
        emit(
            when (exchangeRatesRepository.exchangeCount()) {
                0 -> {
                    storeDefaultExchange()
                    exchangeRatesRepository.fetchExchangeFromDb()
                }
                else -> exchangeRatesRepository.fetchExchangeFromDb()
            }
        )
    }

    private fun storeDefaultExchange() = CoroutineScope(Dispatchers.IO).launch {
        exchangeRatesRepository.storeExchangeInDb(defaultExchange())
    }

    fun fetchRateForBase(base: String) = liveData(Dispatchers.IO) {
        emit(exchangeRatesRepository.fetchRateForBaseFromDb(base))
    }
    // endregion

}
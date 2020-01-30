package no.mhl.clarence.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.mhl.clarence.data.local.converters.Converters
import no.mhl.clarence.data.model.Rate
import no.mhl.clarence.data.model.mapToRate
import no.mhl.clarence.repository.ExchangeRatesRepository
import no.mhl.clarence.util.generateCurrencyList
import java.lang.Exception

class HomeViewModel(
    private val exchangeRatesRepository: ExchangeRatesRepository
) : ViewModel() {

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

    // region Locally Store Rates
    private fun storeAllRates(rates: List<Rate>) = CoroutineScope(Dispatchers.IO).launch {
        exchangeRatesRepository.deleteRatesFromDb()
        exchangeRatesRepository.storeAllRatesInDb(rates)
    }
    // endregion

}
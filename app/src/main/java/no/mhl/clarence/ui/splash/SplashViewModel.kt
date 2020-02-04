package no.mhl.clarence.ui.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.mhl.clarence.data.model.Rate
import no.mhl.clarence.data.model.mapToRate
import no.mhl.clarence.repository.ExchangeRatesRepository
import no.mhl.clarence.util.generateCurrencyList
import java.lang.Exception

class SplashViewModel(
    private val exchangeRatesRepository: ExchangeRatesRepository
) : ViewModel() {

    // region Live Data Properties
    val downloadStatus: MutableLiveData<Int> = MutableLiveData()
    // endregion

    // region Initialisation
    init { downloadCurrencyRates() }
    // endregion

    // region Rate Downloading
    private fun downloadCurrencyRates() = CoroutineScope(Dispatchers.IO).launch {
        downloadStatus.postValue(0)
        val rates: MutableList<Rate> = mutableListOf()

        generateCurrencyList().forEach { currency ->
            try {
                val latest = exchangeRatesRepository.fetchLatestExchangeRatesForBase(currency.name)
                rates.add(mapToRate(latest))
            } catch (exception: Exception) {
                Log.e("DownloadRates -> ", "Exception: ", exception)
            }
        }

        downloadStatus.postValue(1)
    }
    // endregion

}
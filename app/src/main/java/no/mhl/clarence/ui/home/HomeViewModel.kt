package no.mhl.clarence.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.mhl.clarence.data.model.defaultExchange
import no.mhl.clarence.repository.ExchangeRatesRepository

class HomeViewModel(
    private val exchangeRatesRepository: ExchangeRatesRepository
) : ViewModel() {

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
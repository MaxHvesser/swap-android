package no.mhl.clarence.ui.currencyselection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.mhl.clarence.data.model.Currency
import no.mhl.clarence.repository.ExchangeRatesRepository

class CurrencySelectionViewModel(
    private val exchangeRatesRepository: ExchangeRatesRepository
) : ViewModel() {

    // region Update Exchange
    fun updateExchangeBase(currency: Currency) = CoroutineScope(Dispatchers.IO).launch {
        val currentExchange = exchangeRatesRepository.fetchExchangeFromDb()
        currentExchange?.let { updatedExchange ->
            updatedExchange.from = currency
            exchangeRatesRepository.updateExchange(updatedExchange)
        }
    }

    fun updateExchangeToCurrency(currency: Currency) = CoroutineScope(Dispatchers.IO).launch {
        val currentExchange = exchangeRatesRepository.fetchExchangeFromDb()
        currentExchange?.let { updatedExchange ->
            updatedExchange.to = currency
            exchangeRatesRepository.updateExchange(updatedExchange)
        }
    }
    // endregion

}
package no.mhl.clarence.ui.currencyselection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import no.mhl.clarence.data.model.Latest
import no.mhl.clarence.repository.ExchangeRatesRepository

class CurrencySelectionViewModel(
    private val exchangeRatesRepository: ExchangeRatesRepository
) : ViewModel() {

    // region Fetch locally stored rates
    fun fetchRatesFromDatabase() = liveData<Latest> {
        val latest = exchangeRatesRepository.fetchLocalLatestRates()
        emit(latest)
    }
    // endregion

}
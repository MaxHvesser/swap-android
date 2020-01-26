package no.mhl.clarence.ui.home

import androidx.lifecycle.ViewModel
import no.mhl.clarence.repository.ExchangeRatesRepository

class HomeViewModel(
    private val exchangeRatesRepository: ExchangeRatesRepository
) : ViewModel() {

    // region Get Latest Rates
    suspend fun downloadLatestExchangeRates() =
        exchangeRatesRepository.fetchLatestExchangeRates()
    // endregion

}
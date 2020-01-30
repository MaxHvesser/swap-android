package no.mhl.clarence.ui.currencyselection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import no.mhl.clarence.repository.ExchangeRatesRepository

class CurrencySelectionViewModel(
    private val exchangeRatesRepository: ExchangeRatesRepository
) : ViewModel()
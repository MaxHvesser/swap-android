package no.mhl.clarence.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import no.mhl.clarence.data.model.Exchange
import no.mhl.clarence.data.model.Rate
import no.mhl.clarence.databinding.FragmentHomeBinding
import no.mhl.clarence.ui.views.currencydisplay.CurrencyDisplay
import no.mhl.clarence.ui.views.currencyswap.CurrencySwap
import no.mhl.clarence.ui.views.keypad.KeypadKey
import no.mhl.clarence.util.consumeKeyForDisplay
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    // region Properties
    private val homeViewModel: HomeViewModel by sharedViewModel()
    private lateinit var exchange: Exchange
    private lateinit var ratesForExchange: Rate
    // endregion

    // region View Properties
    private lateinit var binding: FragmentHomeBinding
    // endregion

    // region Initialisation
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        setupView()
        fetchCurrentExchange()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.requestApplyInsets(view)
    }
    // endregion

    // region View Setup
    private fun setupView() {
        setupViewInsets()
        setupKeypadView()
        setupCurrencyChips()
        restoreFragmentState()
    }

    private fun setupViewInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.homeKeypadParent) { v, insets ->
            v.updatePadding(bottom = insets.systemWindowInsetBottom)
            insets
        }
    }
    // endregion

    // region Keypad View Setup
    private fun setupKeypadView() {
        binding.homeKeypadView.keypadClickEvent.observe(viewLifecycleOwner, Observer { key ->
            consumeKeyForDisplay(key, binding.homeDisplayCurrency.primary)

            if (key == KeypadKey.BACKSPACE) {
                consumeKeyForDisplay(key, binding.homeDisplayCurrency.secondary)
            }

            convertCurrency()
        })
    }
    // endregion

    // region Currency Display Setup
    private fun setupCurrencyChips() {
        binding.homeDisplaySwap.primary.setOnClickListener { openCurrencySelection(true) }
        binding.homeDisplaySwap.secondary.setOnClickListener { openCurrencySelection() }
    }
    // endregion

    // region Open Currency Selection
    private fun openCurrencySelection(base: Boolean = false) {
        val directions = HomeFragmentDirections.actionHomeFragmentToCurrencySelectionFragment().setIsBaseSelection(base)
        findNavController().navigate(directions)
    }
    // endregion

    // region Data Setup
    private fun fetchCurrentExchange() {
        homeViewModel.fetchCurrentExchange().observe(viewLifecycleOwner, Observer {
            it?.let { exchange ->
                this.exchange = exchange
                fetchRateForBase()
            }
        })
    }
    
    private fun fetchRateForBase() {
        homeViewModel.fetchRateForBase(exchange.from.name).observe(viewLifecycleOwner, Observer {
            it?.let { rate ->
                ratesForExchange = rate
                setupCurrencyDetails()
            }
        })
    }
    // endregion

    // region Exchange Conversion
    private fun convertCurrency() {
        if (::ratesForExchange.isInitialized) {
            val currencyValue = ratesForExchange.values.find { it.name == exchange.to.name }
            currencyValue?.let {
                binding.homeDisplayCurrency.convertValueAndFormat(it.value)
            }
        }
    }
    // endregion

    // region Fragment State IO
    private fun saveFragmentState() {
        homeViewModel.valueToExchangeAsString = binding.homeDisplayCurrency.primary.value
        homeViewModel.exchangedValueAsString = binding.homeDisplayCurrency.secondary.value
    }

    private fun restoreFragmentState() {
        binding.homeDisplayCurrency.primary.value = homeViewModel.valueToExchangeAsString
        binding.homeDisplayCurrency.secondary.value = homeViewModel.exchangedValueAsString
    }
    // endregion

    override fun onPause() {
        super.onPause()
        saveFragmentState()
    }
    // endregion

    // region Setup Currency Details
    private fun setupCurrencyDetails() {
        binding.homeDisplayCurrency.primary.name = exchange.from.fullName
        binding.homeDisplayCurrency.secondary.name = exchange.to.fullName
        binding.homeDisplaySwap.setupPrimaryChipForCurrency(exchange.from)
        binding.homeDisplaySwap.setupSecondaryChipForCurrency(exchange.to)
    }
    // endregion

}
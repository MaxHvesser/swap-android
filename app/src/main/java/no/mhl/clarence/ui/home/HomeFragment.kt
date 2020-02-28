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
import no.mhl.clarence.ui.views.keypad.KeypadKey
import no.mhl.clarence.util.consumeKeyForDisplay
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    // region Properties
    private val homeViewModel: HomeViewModel by viewModel()
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
            consumeKeyForDisplay(key, binding.homeCurrencyDisplayPrimary)

            if (key == KeypadKey.BACKSPACE) {
                consumeKeyForDisplay(key, binding.homeCurrencyDisplaySecondary)
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
                CurrencyDisplay.convertValueAndFormat(
                    binding.homeCurrencyDisplayPrimary,
                    binding.homeCurrencyDisplaySecondary,
                    it.value
                )
            }
        }
    }
    // endregion

    // region Setup Currency Details
    private fun setupCurrencyDetails() {
        binding.homeCurrencyDisplayPrimary.name = exchange.from.fullName
        binding.homeCurrencyDisplaySecondary.name = exchange.to.fullName
        binding.homeDisplaySwap.setupPrimaryChipForCurrency(exchange.from)
        binding.homeDisplaySwap.setupSecondaryChipForCurrency(exchange.to)
    }
    // endregion

}
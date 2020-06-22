package no.mhl.swap.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import no.mhl.swap.data.model.Exchange
import no.mhl.swap.data.model.Rate
import no.mhl.swap.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

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
        setupCurrencyDisplay()
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
            binding.homeDisplayCurrency.consumeKeyEvent(key)
            convertCurrency()
        })
    }
    // endregion

    // region Currency Display Setup
    private fun setupCurrencyDisplay() {
        binding.homeDisplayCurrency.primary.setOnClickListener { openCurrencySelection(true) }
        binding.homeDisplayCurrency.secondary.setOnClickListener { openCurrencySelection() }
        binding.homeDisplayCurrency.swapFab.setOnClickListener {
            binding.homeDisplayCurrency.animateSwap()
            val exchange = Exchange(0, exchange.to, exchange.from)
            this.exchange = exchange
            homeViewModel.replaceExchange(exchange)
            fetchRateForBase(false)
        }
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
    
    private fun fetchRateForBase(initialLoad: Boolean = true) {
        homeViewModel.fetchRateForBase(exchange.from.name).observe(viewLifecycleOwner, Observer {
            it?.let { rate ->
                ratesForExchange = rate
                if (initialLoad) { setupCurrencyDetails() } else { convertCurrency() }
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
    }
    // endregion

}
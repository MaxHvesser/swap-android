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
import com.google.android.material.snackbar.Snackbar
import no.mhl.clarence.R
import no.mhl.clarence.data.model.Exchange
import no.mhl.clarence.data.model.Rate
import no.mhl.clarence.databinding.FragmentHomeBinding
import no.mhl.clarence.ui.views.keypad.KeypadKey
import no.mhl.clarence.ui.views.snackbar.makeClarenceSnackbar
import no.mhl.clarence.util.consumeKeyForDisplay
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    // region Properties
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var exchange: Exchange
    private lateinit var ratesForExchange: Rate
    private lateinit var snackbar: Snackbar
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
        //listenForDownload()

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
        setupCurrencyDisplayPrimary()
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
            } else {
                convertCurrency()
            }

        })
    }
    // endregion

    // region Currency Display Setup
    private fun setupCurrencyDisplayPrimary() {
        binding.homeCurrencyDisplayPrimary.currencySelectionClick.observe(
            viewLifecycleOwner,
            Observer {
                openCurrencySelection()
            })
    }
    // endregion

    // region Open Currency Selection
    private fun openCurrencySelection() {
        findNavController().navigate(R.id.action_homeFragment_to_currencySelectionFragment)
    }
    // endregion

    // region Data Setup
    private fun listenForDownload() {
        homeViewModel.ratesDownloading.observe(viewLifecycleOwner, Observer { status ->
            when (status) {
                0 -> setupLoadingSnackbar()
                1 -> fetchCurrentExchange()
            }
        })
    }

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
                snackbar.dismiss()
            }
        })
    }
    // endregion

    // region Snackbar
    private fun setupLoadingSnackbar() {
        snackbar = makeClarenceSnackbar(binding.homeSnackbarContainer, "Downloading latest currencies", Snackbar.LENGTH_INDEFINITE)
        snackbar.show()
    }
    // endregion

    // region Exchange Conversion
    private fun convertCurrency() {
        if (::ratesForExchange.isInitialized) {
            val currencyValue = ratesForExchange.values.find { it.name == "NOK" }
            currencyValue?.let {
                val exchangeValue = binding.homeCurrencyDisplayPrimary.getText().toFloat() * it.value
                binding.homeCurrencyDisplaySecondary.setText(exchangeValue.toString())
            }
        }
    }
    // endregion

}
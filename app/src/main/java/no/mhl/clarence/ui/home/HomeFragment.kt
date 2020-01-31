package no.mhl.clarence.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import no.mhl.clarence.R
import no.mhl.clarence.data.model.Currency
import no.mhl.clarence.data.model.Exchange
import no.mhl.clarence.data.model.defaultExchange
import no.mhl.clarence.data.remote.common.Status
import no.mhl.clarence.databinding.FragmentHomeBinding
import no.mhl.clarence.ui.views.currencydisplay.CurrencyDisplay
import no.mhl.clarence.ui.views.keypad.KeypadKey
import no.mhl.clarence.ui.views.keypad.KeypadView
import no.mhl.clarence.util.consumeKeyForDisplay
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    // region Properties
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var exchange: Exchange
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
        binding = FragmentHomeBinding.inflate(layoutInflater)

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
                binding.homeCurrencyDisplaySecondary.appendValue("1")
            }

        })
    }
    // endregion

    // region Currency Display Setup
    private fun setupCurrencyDisplayPrimary() {
        binding.homeCurrencyDisplayPrimary.currencySelectionClick.observe(viewLifecycleOwner, Observer {
            openCurrencySelection()
        })
    }
    // endregion

    // region Open Currency Selection
    private fun openCurrencySelection() {
        findNavController().navigate(R.id.action_homeFragment_to_currencySelectionFragment)
    }
    // endregion

    // region Pre Fetching Exchange Rates
    private fun fetchCurrentExchange() {
        homeViewModel.fetchCurrentExchange()
            .observe(viewLifecycleOwner, Observer { if (it != null) exchange = it })
    }
    // endregion

}
package no.mhl.clarence.ui.home

import android.os.Bundle
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
import no.mhl.clarence.ui.views.currencydisplay.CurrencyDisplay
import no.mhl.clarence.ui.views.keypad.KeypadKey
import no.mhl.clarence.ui.views.keypad.KeypadView
import no.mhl.clarence.util.consumeKeyForDisplay
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    // region Properties
    private val homeViewModel: HomeViewModel by viewModel()
    // endregion

    // region View Properties
    private lateinit var keypadView: KeypadView
    private lateinit var currencyDisplayPrimary: CurrencyDisplay
    // endregion

    // region Initialisation
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        setupView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.requestApplyInsets(view)
    }
    // endregion

    // region View Setup
    private fun setupView(view: View) {
        keypadView = view.findViewById(R.id.home_keypad_view)
        currencyDisplayPrimary = view.findViewById(R.id.home_currency_display_primary)

        setupViewInsets(view.findViewById(R.id.home_keypad_parent))
        setupKeypadView(keypadView)
        setupCurrencyDisplayPrimary()
    }

    private fun setupViewInsets(keypadParent: ConstraintLayout) {
        ViewCompat.setOnApplyWindowInsetsListener(keypadParent) { v, insets ->
            v.updatePadding(bottom = insets.systemWindowInsetBottom)
            insets
        }
    }
    // endregion

    // region Keypad View Setup
    private fun setupKeypadView(keypadView: KeypadView) {
        keypadView.keypadClickEvent.observe(viewLifecycleOwner, Observer { key ->
            consumeKeyForDisplay(key, currencyDisplayPrimary)
        })
    }
    // endregion

    // region Currency Display Setup
    private fun setupCurrencyDisplayPrimary() {
        currencyDisplayPrimary.currencySelectionClick.observe(viewLifecycleOwner, Observer {
            openCurrencySelection()
        })
    }
    // endregion

    // region Open Currency Selection
    private fun openCurrencySelection() {
        findNavController().navigate(R.id.action_homeFragment_to_currencySelectionFragment)
    }
    // endregion

}
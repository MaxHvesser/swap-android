package no.mhl.clarence.ui.currencyselection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import no.mhl.clarence.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencySelectionFragment : Fragment() {

    // region Properties
    private val currencySelectionViewModel: CurrencySelectionViewModel by viewModel()
    // endregion

    // region View Properties
    private lateinit var parent: ConstraintLayout
    // endregion

    // region Initialisation
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_currency_selection, container, false)
        setupViews(view)
        fetchLocalRates()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.requestApplyInsets(view)
    }
    // endregion

    // region View Setup
    private fun setupViews(view: View) {
        parent = view.findViewById(R.id.parent)

        setupViewInsets()
    }

    private fun setupViewInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(parent) { v, insets ->
            v.updatePadding(
                top = insets.systemWindowInsetTop,
                bottom = insets.systemWindowInsetBottom
            )
            insets
        }
    }
    // endregion

    // region Fetch locally stored rates
    private fun fetchLocalRates() {
        currencySelectionViewModel.fetchRatesFromDatabase().observe(viewLifecycleOwner, Observer { latest ->
            val t = latest.rates
        })
    }
    // endregion

}
package no.mhl.clarence.ui.currencyselection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import no.mhl.clarence.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencySelectionFragment : Fragment() {

    // region Properties
    private val currencySelectionViewModel: CurrencySelectionViewModel by viewModel()
    // endregion

    // region Initialisation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_currency_selection, container, false)
        setupViews(view)
        return view
    }
    // endregion

    // region View Setup
    private fun setupViews(view: View) {

    }
    // endregion

}
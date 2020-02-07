package no.mhl.clarence.ui.currencyselection

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import no.mhl.clarence.data.model.Currency
import no.mhl.clarence.databinding.FragmentCurrencySelectionBinding
import no.mhl.clarence.ui.currencyselection.adapter.CurrencyRecyclerAdapter
import no.mhl.clarence.util.generateCurrencyList
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencySelectionFragment : Fragment() {

    // region Properties
    private val currencySelectionViewModel: CurrencySelectionViewModel by viewModel()
    private val linearLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
    private val currencyRates: List<Currency> = generateCurrencyList()
    private val currencyRecyclerAdapter: CurrencyRecyclerAdapter = CurrencyRecyclerAdapter(currencyRates)
    // endregion

    // region View Properties
    private lateinit var binding: FragmentCurrencySelectionBinding
    // endregion

    // region Initialisation
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencySelectionBinding.inflate(inflater, container, false)

        setupViews()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.requestApplyInsets(view)
    }
    // endregion

    // region View Setup
    private fun setupViews() {
        setupViewInsets()
        setupCurrencyRecycler()
        setupSearchField()
    }

    private fun setupViewInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.parent) { v, insets ->
            v.updatePadding(
                top = insets.systemWindowInsetTop,
                bottom = insets.systemWindowInsetBottom
            )
            insets
        }
    }
    // endregion

    // region Recycler Setup
    private fun setupCurrencyRecycler() {
        binding.currencyRecycler.apply {
            adapter = currencyRecyclerAdapter
            layoutManager = linearLayoutManager
        }

        currencyRecyclerAdapter.currencyClickEvent.observe(viewLifecycleOwner, Observer { currency ->
            findNavController().popBackStack()
        })
    }
    // endregion

    // region Search Field Setup
    private fun setupSearchField() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                currencyRecyclerAdapter.filter.filter(s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun afterTextChanged(s: Editable?) { }
        })
    }
    // endregion

    // region Misc
    private fun updateExchange() {
        arguments?.let {
            val isBaseSelection = CurrencySelectionFragmentArgs.fromBundle(it).isBaseSelection
        }
    }
    // endregion

}
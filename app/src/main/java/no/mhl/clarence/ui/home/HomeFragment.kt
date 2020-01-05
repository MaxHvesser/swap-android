package no.mhl.clarence.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import no.mhl.clarence.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    // region Properties
    private val homeViewModel: HomeViewModel by viewModel()
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
    // endregion

    // region View Setup
    private fun setupView(view: View) {

    }
    // endregion

}
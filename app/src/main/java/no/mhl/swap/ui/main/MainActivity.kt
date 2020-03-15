package no.mhl.swap.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import no.mhl.swap.R
import no.mhl.swap.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // region Properties
    private val mainViewModel: MainViewModel by viewModel()
    // endregion

    // region View Properties
    private lateinit var binding: ActivityMainBinding
    // endregion

    // region Initialisation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEdgeToEdge()
    }
    // endregion

    // region View Setup
    private fun setupEdgeToEdge() {
        binding.mainParent.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }
    // endregion

    // region Back Navigation
    override fun onSupportNavigateUp() = findNavController(R.id.main_home_fragment).navigateUp()
    // endregion

}

package no.mhl.clarence.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import no.mhl.clarence.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // region Properties
    private val mainViewModel: MainViewModel by viewModel()
    // endregion

    // region Initialisation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    // endregion

    // region Back Navigation
    override fun onSupportNavigateUp() = findNavController(R.id.main_home_fragment).navigateUp()
    // endregion

}

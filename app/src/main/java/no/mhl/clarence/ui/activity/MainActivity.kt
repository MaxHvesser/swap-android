package no.mhl.clarence.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import no.mhl.clarence.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // region Properties
    private val mainViewModel: MainViewModel by viewModel()
    // endregion

    // region View Properties
    private val viewParent by lazy { findViewById<ConstraintLayout>(R.id.main_parent) }
    // endregion

    // region Initialisation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEdgeToEdge()
    }
    // endregion

    // region View Setup
    private fun setupEdgeToEdge() {
        viewParent.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }
    // endregion

    // region Back Navigation
    override fun onSupportNavigateUp() = findNavController(R.id.main_home_fragment).navigateUp()
    // endregion

}

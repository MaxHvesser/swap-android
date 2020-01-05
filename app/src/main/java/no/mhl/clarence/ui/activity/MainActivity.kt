package no.mhl.clarence.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

}

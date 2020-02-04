package no.mhl.clarence.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import no.mhl.clarence.databinding.ActivitySplashBinding
import no.mhl.clarence.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    // region Properties
    private val splashViewModel: SplashViewModel by viewModel()
    // endregion

    // region View Properties
    private lateinit var binding: ActivitySplashBinding
    // endregion

    // region Initialisation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeRatesDownloadStatus()
    }
    // endregion

    // region View Setup
    private fun observeRatesDownloadStatus() {
        splashViewModel.downloadStatus.observe(this, Observer {  status ->
            when (status) {
                1 -> startMainActivity()
            }
        })
    }
    // endregion

    // region Start Main Activity
    private fun startMainActivity() {
        startActivity(Intent(baseContext, MainActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
    // endregion

}
package no.mhl.swap.application

import android.app.Application
import no.mhl.swap.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SwapApp : Application() {

    // region Initialisation
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SwapApp)
            modules(
                listOf(
                    splashModule,
                    mainModule,
                    homeModule,
                    currencySelectionModule,
                    networkModule,
                    exchangeRatesModule,
                    databaseModule
                )
            )
        }
    }
    // endregion

}
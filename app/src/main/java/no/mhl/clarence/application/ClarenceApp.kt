package no.mhl.clarence.application

import android.app.Application
import no.mhl.clarence.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ClarenceApp : Application() {

    // region Initialisation
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ClarenceApp)
            modules(
                listOf(
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
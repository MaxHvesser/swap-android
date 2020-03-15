package no.mhl.swap.di

import androidx.room.Room
import no.mhl.swap.application.Constants
import no.mhl.swap.data.local.SwapDatabase
import no.mhl.swap.data.local.dao.ExchangeDao
import no.mhl.swap.data.local.dao.RateDao
import no.mhl.swap.data.remote.ExchangeRatesService
import no.mhl.swap.repository.ExchangeRatesRepository
import no.mhl.swap.ui.main.MainViewModel
import no.mhl.swap.ui.currencyselection.CurrencySelectionFragment
import no.mhl.swap.ui.home.HomeFragment
import no.mhl.swap.ui.home.HomeViewModel
import no.mhl.swap.ui.splash.SplashViewModel
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// region Splash Module
val splashModule = module {
    factory { SplashViewModel(get()) }
}
// endregion

// region Main Module
val mainModule = module {
    factory { MainViewModel() }
}
// endregion

// region Home Module
val homeModule = module {
    single { HomeFragment() }
    factory { HomeViewModel(get()) }
}
// endregion

// region Currency Selection Module
val currencySelectionModule = module {
    single { CurrencySelectionFragment() }
}
// endregion

// region Network Module
val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideExchangeRatesService(get()) }
    factory { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(Constants.EXCHANGE_RATES_BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient().newBuilder().build()

fun provideExchangeRatesService(retrofit: Retrofit): ExchangeRatesService =
    retrofit.create(ExchangeRatesService::class.java)
// endregion

// region Exchange Rates Module
val exchangeRatesModule = module {
    factory { ExchangeRatesRepository(get(), get(), get()) }
}
// endregion

// region Database Module
val databaseModule = module {
    single { Room.databaseBuilder(get(), SwapDatabase::class.java, "clarence-db").fallbackToDestructiveMigration().build() }
    factory { provideRateDao(get())  }
    factory { provideExchangeDao(get())  }
}

fun provideRateDao(database: SwapDatabase): RateDao = database.rateDao()

fun provideExchangeDao(database: SwapDatabase): ExchangeDao = database.exchangeDao()
// endregion
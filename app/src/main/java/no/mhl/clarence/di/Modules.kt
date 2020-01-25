package no.mhl.clarence.di

import no.mhl.clarence.application.Constants
import no.mhl.clarence.data.remote.ExchangeRatesService
import no.mhl.clarence.repository.ExchangeRatesRepository
import no.mhl.clarence.ui.activity.MainViewModel
import no.mhl.clarence.ui.currencyselection.CurrencySelectionFragment
import no.mhl.clarence.ui.currencyselection.CurrencySelectionViewModel
import no.mhl.clarence.ui.home.HomeFragment
import no.mhl.clarence.ui.home.HomeViewModel
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    factory { CurrencySelectionViewModel() }
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
    factory { ExchangeRatesRepository(get()) }
}
// endregion
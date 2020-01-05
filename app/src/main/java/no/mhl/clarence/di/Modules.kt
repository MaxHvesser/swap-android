package no.mhl.clarence.di

import no.mhl.clarence.ui.activity.MainViewModel
import org.koin.dsl.module

// region Main Module
val mainModule = module {
    factory { MainViewModel() }
}
// endregion
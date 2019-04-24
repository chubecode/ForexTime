package it.chutien.forextime.di

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import it.chutien.forextime.ui.main.MainActivityViewModel
import it.chutien.forextime.ui.list.ForexPriceViewModel

/**
 * Created by ChuTien on ${1/25/2017}.
 */
val viewModelModule = module(override = true) {
    viewModel { MainActivityViewModel() }
    viewModel { ForexPriceViewModel(get()) }
}
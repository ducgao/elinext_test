package com.ducgao.elinexttest.feats.core.di

import com.ducgao.elinexttest.feats.home.presentation.HomeConfigs
import com.ducgao.elinexttest.feats.home.presentation.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { HomeConfigs() }
    viewModel { HomeViewModel(get()) }
}
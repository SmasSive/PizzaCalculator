package com.smassive.pizzacalculator.di

import com.smassive.pizzacalculator.main.ui.viewmodel.MainViewModel
import com.smassive.pizzacalculator.result.ui.model.mapper.DoughViewResultMapper
import com.smassive.pizzacalculator.result.ui.viewmodel.ResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
  viewModel { MainViewModel() }
  viewModel { ResultViewModel(get(), get(), get()) }

  single { DoughViewResultMapper() }
}
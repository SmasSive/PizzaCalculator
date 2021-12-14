package com.smassive.pizzacalculator

import android.app.Application
import com.smassive.pizzacalculator.di.appModule
import com.smassive.pizzacalculator.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PizzaCalculatorApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidLogger()
      androidContext(this@PizzaCalculatorApplication)
      modules(listOf(domainModule, appModule))
    }
  }
}
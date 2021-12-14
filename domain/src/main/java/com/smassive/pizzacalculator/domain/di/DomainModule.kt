package com.smassive.pizzacalculator.domain.di

import com.smassive.pizzacalculator.domain.model.BaseIngredientsBuilder
import com.smassive.pizzacalculator.domain.sourdough.CalculateSourdoughDough
import com.smassive.pizzacalculator.domain.yeast.CalculateYeastDough
import org.koin.dsl.module

val domainModule = module {
  single { CalculateYeastDough(get()) }
  single { CalculateSourdoughDough(get()) }
  single { BaseIngredientsBuilder() }
}
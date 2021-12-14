package com.smassive.pizzacalculator.domain.sourdough.model

import com.smassive.pizzacalculator.domain.model.Ingredient

data class SourdoughDoughResult(
  val weight: Int,
  val count: Int,
  val overallFlour: Ingredient,
  val requiredFlour: Ingredient,
  val sourdough: Sourdough,
  val water: Ingredient,
  val salt: Ingredient,
) {

  data class Sourdough(
    val total: Ingredient,
    val water: Ingredient,
    val flour: Ingredient,
  )
}
package com.smassive.pizzacalculator.domain.yeast.model

import com.smassive.pizzacalculator.domain.model.Ingredient

data class YeastDoughResult(
  val weight: Int,
  val count: Int,
  val flour: Ingredient,
  val water: Ingredient,
  val salt: Ingredient,
  val dryYeast: Ingredient,
  val freshYeast: Ingredient,
)

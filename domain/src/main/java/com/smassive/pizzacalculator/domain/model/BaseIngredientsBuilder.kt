package com.smassive.pizzacalculator.domain.model

class BaseIngredientsBuilder {

  fun build(water: Double, salt: Double): List<DoughRequestModel.Ingredient> {
    return listOf(
      DoughRequestModel.Ingredient("Flour", 1.0),
      DoughRequestModel.Ingredient("Water", water),
      DoughRequestModel.Ingredient("Salt", salt),
    )
  }
}
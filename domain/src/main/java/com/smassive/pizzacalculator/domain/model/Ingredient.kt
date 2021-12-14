package com.smassive.pizzacalculator.domain.model

data class Ingredient(
  val name: String,
  val absolute: Double,
  val percentage: Double?,
)
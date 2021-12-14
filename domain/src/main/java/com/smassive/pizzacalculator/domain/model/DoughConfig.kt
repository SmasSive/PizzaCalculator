package com.smassive.pizzacalculator.domain.model

data class DoughConfig(
  val weight: Int = 200,
  val count: Int = 2,
  val water: Double = 0.6,
  val salt: Double = 0.02,
)

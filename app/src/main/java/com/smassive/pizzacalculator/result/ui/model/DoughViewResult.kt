package com.smassive.pizzacalculator.result.ui.model

sealed class DoughViewResult(
  open val weight: Int,
  open val count: Int,
) {

  data class Yeast(
    override val weight: Int,
    override val count: Int,
    val flour: IngredientView,
    val water: IngredientView,
    val salt: IngredientView,
    val dryYeast: IngredientView,
    val freshYeast: IngredientView,
  ) : DoughViewResult(weight, count)

  data class Sourdough(
    override val weight: Int,
    override val count: Int,
    val overallFlour: IngredientView,
    val requiredFlour: IngredientView,
    val sourdough: SourdoughView,
    val water: IngredientView,
    val salt: IngredientView,
  ) : DoughViewResult(weight, count) {

    data class SourdoughView(
      val total: IngredientView,
      val water: IngredientView,
      val flour: IngredientView,
    )
  }

  data class IngredientView(
    val name: String,
    val percentage: String?,
    val quantity: String,
  )
}

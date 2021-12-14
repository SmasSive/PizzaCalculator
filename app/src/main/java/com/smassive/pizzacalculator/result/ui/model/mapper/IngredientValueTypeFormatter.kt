package com.smassive.pizzacalculator.result.ui.model.mapper

import com.smassive.pizzacalculator.common.ui.model.mapper.mapView
import com.smassive.pizzacalculator.result.ui.model.IngredientValueType

suspend fun Double.format(type: IngredientValueType) = mapView {
  val value = this@format
  when (type) {
    IngredientValueType.PERCENTAGE -> (value * 100).toBigDecimal().setScale(2).toString() + type.value
    IngredientValueType.GRAMS -> value.toString() + " ${type.value}"
  }
}
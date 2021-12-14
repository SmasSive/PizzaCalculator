package com.smassive.pizzacalculator.domain.model.mapper

import java.math.RoundingMode

fun Double.round(decimals: Int): Double {
  return this.toBigDecimal().setScale(decimals, RoundingMode.HALF_UP).toDouble()
}
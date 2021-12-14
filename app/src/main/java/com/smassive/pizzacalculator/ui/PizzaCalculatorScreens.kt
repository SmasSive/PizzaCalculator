package com.smassive.pizzacalculator.ui

enum class PizzaCalculatorScreens {
  ConfigScreen,
  ResultScreen;

  companion object {
    fun fromRoute(route: String?): PizzaCalculatorScreens =
      when (route?.substringBefore("/")) {
        ConfigScreen.name -> ConfigScreen
        ResultScreen.name -> ResultScreen
        null -> ConfigScreen
        else -> throw IllegalArgumentException("Route $route is not recognized.")
      }
  }
}
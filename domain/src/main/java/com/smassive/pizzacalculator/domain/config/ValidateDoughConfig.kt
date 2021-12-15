package com.smassive.pizzacalculator.domain.config

import com.smassive.pizzacalculator.domain.core.DispatchersProvider
import com.smassive.pizzacalculator.domain.core.UseCase
import kotlinx.coroutines.withContext

class ValidateDoughConfig : UseCase() {

  suspend operator fun invoke(numberOfPizzas: String, weightPerPizza: String): Boolean = withContext(DispatchersProvider.computation) {
    areNotBlank(numberOfPizzas, weightPerPizza) && areNumbers(numberOfPizzas, weightPerPizza)
  }

  private fun areNotBlank(numberOfPizzas: String, weightPerPizza: String) = numberOfPizzas.isNotBlank() && weightPerPizza.isNotBlank()

  private fun areNumbers(numberOfPizzas: String, weightPerPizza: String): Boolean {
    return try {
      numberOfPizzas.toInt()
      weightPerPizza.toInt()
      true
    } catch (nfe: NumberFormatException) {
      false
    }
  }
}
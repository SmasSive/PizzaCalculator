package com.smassive.pizzacalculator.main.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.smassive.pizzacalculator.common.ui.flowOnViewModel
import com.smassive.pizzacalculator.domain.model.DoughConfig
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {

  var doughConfig = DoughConfig()

  fun getDoughConfig(): Flow<State> = flowOnViewModel { emit(State.Loaded(doughConfig)) }

  fun validateForm(numberOfPizzas: String, weightPerPizza: String): Flow<Boolean> = flowOnViewModel {
    val valid = areNotBlank(numberOfPizzas, weightPerPizza) && areNumbers(numberOfPizzas, weightPerPizza)
    if (valid) doughConfig = DoughConfig(weightPerPizza.toInt(), numberOfPizzas.toInt())
    emit(valid)
  }

  private fun areNotBlank(numberOfPizzas: String, weightPerPizza: String) =
    numberOfPizzas.isNotBlank() && weightPerPizza.isNotBlank()

  private fun areNumbers(numberOfPizzas: String, weightPerPizza: String): Boolean {
    return try {
      numberOfPizzas.toInt()
      weightPerPizza.toInt()
      true
    } catch (nfe: NumberFormatException) {
      false
    }
  }

  sealed class State {
    object Loading : State()
    class Loaded(val doughConfig: DoughConfig) : State()
  }
}
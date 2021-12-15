package com.smassive.pizzacalculator.main.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.smassive.pizzacalculator.common.ui.flowOnViewModel
import com.smassive.pizzacalculator.domain.config.ValidateDoughConfig
import com.smassive.pizzacalculator.domain.model.DoughConfig
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val validateDoughConfig: ValidateDoughConfig) : ViewModel() {

  var doughConfig = DoughConfig()

  fun getDoughConfig(): Flow<State> = flowOnViewModel { emit(State.Loaded(doughConfig)) }

  fun validateForm(numberOfPizzas: String, weightPerPizza: String): Flow<Boolean> = flowOnViewModel {
    val valid = validateDoughConfig(numberOfPizzas, weightPerPizza)
    if (valid) doughConfig = DoughConfig(weightPerPizza.toInt(), numberOfPizzas.toInt())
    emit(valid)
  }

  sealed class State {
    object Loading : State()
    class Loaded(val doughConfig: DoughConfig) : State()
  }
}
package com.smassive.pizzacalculator.main.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.smassive.pizzacalculator.common.ui.flowOnViewModel
import com.smassive.pizzacalculator.domain.model.DoughConfig
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {

  fun getDoughConfig(): Flow<State> = flowOnViewModel { emit(State.Success(DoughConfig())) }

  sealed class State {
    object Loading : State()
    class Success(val doughConfig: DoughConfig) : State()
  }
}
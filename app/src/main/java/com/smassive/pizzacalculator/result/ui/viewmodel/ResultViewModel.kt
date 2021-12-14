package com.smassive.pizzacalculator.result.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.smassive.pizzacalculator.common.ui.flowOnViewModel
import com.smassive.pizzacalculator.domain.model.DoughConfig
import com.smassive.pizzacalculator.domain.model.DoughRequestModel
import com.smassive.pizzacalculator.domain.sourdough.CalculateSourdoughDough
import com.smassive.pizzacalculator.domain.sourdough.model.SourdoughDoughResult
import com.smassive.pizzacalculator.domain.yeast.CalculateYeastDough
import com.smassive.pizzacalculator.domain.yeast.model.YeastDoughResult
import com.smassive.pizzacalculator.result.ui.model.DoughViewResult
import com.smassive.pizzacalculator.result.ui.model.mapper.DoughViewResultMapper
import kotlinx.coroutines.flow.Flow

class ResultViewModel(
  private val calculateYeastDough: CalculateYeastDough,
  private val calculateSourdoughDough: CalculateSourdoughDough,
  private val doughViewResultMapper: DoughViewResultMapper,
) : ViewModel() {

  fun onYeastRequested(weightPerPizza: Int, numberOfPizzas: Int): Flow<State> = flowOnViewModel {
    val yeastDoughRequest = DoughRequestModel.Yeast(
      doughConfig = DoughConfig(weight = weightPerPizza, count = numberOfPizzas)
    )
    val yeastDoughResult: YeastDoughResult = calculateYeastDough(yeastDoughRequest)

    emit(State.Calculated(doughViewResultMapper.map(yeastDoughResult)))
  }

  fun onSourdoughRequested(weightPerPizza: Int, numberOfPizzas: Int): Flow<State> = flowOnViewModel {
    val sourdoughDoughRequest = DoughRequestModel.Sourdough(
      doughConfig = DoughConfig(weight = weightPerPizza, count = numberOfPizzas)
    )
    val sourdoughDoughResult: SourdoughDoughResult = calculateSourdoughDough(sourdoughDoughRequest)

    emit(State.Calculated(doughViewResultMapper.map(sourdoughDoughResult)))
  }

  sealed class State {
    object Calculating : State()
    class Calculated(val doughResult: DoughViewResult) : State()
  }
}
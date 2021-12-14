package com.smassive.pizzacalculator.domain.sourdough

import com.smassive.pizzacalculator.domain.core.UseCase
import com.smassive.pizzacalculator.domain.model.BaseIngredientsBuilder
import com.smassive.pizzacalculator.domain.model.DoughRequestModel
import com.smassive.pizzacalculator.domain.model.Ingredient
import com.smassive.pizzacalculator.domain.model.mapper.round
import com.smassive.pizzacalculator.domain.sourdough.model.SourdoughDoughResult

class CalculateSourdoughDough(private val baseIngredientsBuilder: BaseIngredientsBuilder) : UseCase() {

  suspend operator fun invoke(sourdoughDoughRequest: DoughRequestModel.Sourdough): SourdoughDoughResult = execute {
    val doughConfig = sourdoughDoughRequest.doughConfig
    val totalPercent = baseIngredientsBuilder.build(doughConfig.water, doughConfig.salt).sumOf { it.percentage }
    val flourPerPizza = doughConfig.weight / totalPercent
    val saltPerPizza = flourPerPizza * doughConfig.salt
    val finalWaterPerPizza = flourPerPizza * doughConfig.water
    val sourdoughMassPerPizza = flourPerPizza * sourdoughDoughRequest.sourdoughPercent
    val sourdoughWater = sourdoughMassPerPizza / (sourdoughDoughRequest.sourdoughHydration * 2)
    val sourdoughFlour = sourdoughMassPerPizza / (sourdoughDoughRequest.sourdoughHydration * 2)
    val addedFlourPerPizza = flourPerPizza - sourdoughFlour
    val addedWaterPerPizza = finalWaterPerPizza - sourdoughWater

    val numberOfPizzas = doughConfig.count
    val totalFlour = flourPerPizza * numberOfPizzas
    val totalWater = addedWaterPerPizza * numberOfPizzas
    val totalSalt = saltPerPizza * numberOfPizzas
    val totalAddedFlour = addedFlourPerPizza * numberOfPizzas
    val totalSourdoughMass = sourdoughMassPerPizza * numberOfPizzas
    val totalSourdoughWater = sourdoughWater * numberOfPizzas
    val totalSourdoughFlour = sourdoughFlour * numberOfPizzas

    SourdoughDoughResult(
      doughConfig.weight,
      doughConfig.count,
      Ingredient("Overall flour", totalFlour.round(2), 1.0),
      Ingredient("Required flour", totalAddedFlour.round(2), null),
      SourdoughDoughResult.Sourdough(
        Ingredient("Sourdough", totalSourdoughMass.round(2), sourdoughDoughRequest.sourdoughPercent),
        Ingredient(
          "...Water",
          totalSourdoughWater.round(2),
          sourdoughDoughRequest.sourdoughPercent * (sourdoughDoughRequest.sourdoughHydration / 2),
        ),
        Ingredient(
          "...Flour",
          totalSourdoughFlour.round(2),
          sourdoughDoughRequest.sourdoughPercent * (sourdoughDoughRequest.sourdoughHydration / 2),
        ),
      ),
      Ingredient("Water", totalWater.round(2), doughConfig.water),
      Ingredient("Salt", totalSalt.round(2), doughConfig.salt)
    )
  }
}
package com.smassive.pizzacalculator.domain.yeast

import com.smassive.pizzacalculator.domain.core.UseCase
import com.smassive.pizzacalculator.domain.model.BaseIngredientsBuilder
import com.smassive.pizzacalculator.domain.model.DoughRequestModel
import com.smassive.pizzacalculator.domain.model.Ingredient
import com.smassive.pizzacalculator.domain.model.mapper.round
import com.smassive.pizzacalculator.domain.yeast.model.YeastDoughResult

class CalculateYeastDough(private val baseIngredientsBuilder: BaseIngredientsBuilder) : UseCase() {

  suspend operator fun invoke(yeastDoughRequest: DoughRequestModel.Yeast): YeastDoughResult = execute {
    val doughConfig = yeastDoughRequest.doughConfig
    val totalPercent = baseIngredientsBuilder.build(doughConfig.water, doughConfig.salt).sumOf { it.percentage }
    val flourPerPizza = doughConfig.weight / totalPercent
    val saltPerPizza = flourPerPizza * doughConfig.salt
    val finalWaterPerPizza = flourPerPizza * doughConfig.water
    val yeastPerPizza = flourPerPizza * yeastDoughRequest.dryYeastPercent
    val freshYeastPerPizza = flourPerPizza * yeastDoughRequest.freshYeastPercent

    val numberOfPizzas = doughConfig.count
    val totalFlour = flourPerPizza * numberOfPizzas
    val totalWater = finalWaterPerPizza * numberOfPizzas
    val totalSalt = saltPerPizza * numberOfPizzas
    val totalYeast = yeastPerPizza * numberOfPizzas
    val totalFreshYeast = freshYeastPerPizza * numberOfPizzas

    YeastDoughResult(
      doughConfig.weight,
      doughConfig.count,
      Ingredient("Flour", totalFlour.round(2), 1.0),
      Ingredient("Water", totalWater.round(2), doughConfig.water),
      Ingredient("Salt", totalSalt.round(2), doughConfig.salt),
      Ingredient("Dry Yeast", totalYeast.round(2), yeastDoughRequest.dryYeastPercent),
      Ingredient("...or Fresh Yeast", totalFreshYeast.round(2), yeastDoughRequest.freshYeastPercent),
    )
  }
}

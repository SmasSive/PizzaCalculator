package com.smassive.pizzacalculator.domain.model

private const val dryYeast = 0.0005
private const val freshYeast = dryYeast * 3

sealed class DoughRequestModel(open val doughConfig: DoughConfig) {

  data class Yeast(
    override val doughConfig: DoughConfig,
    val dryYeastPercent: Double = dryYeast,
    val freshYeastPercent: Double = freshYeast,
  ) : DoughRequestModel(doughConfig)

  data class Sourdough(
    override val doughConfig: DoughConfig,
    val sourdoughPercent: Double = 0.2,
    val sourdoughHydration: Double = 1.0,
  ) : DoughRequestModel(doughConfig)

  data class Ingredient(
    val name: String,
    val percentage: Double,
  )
}

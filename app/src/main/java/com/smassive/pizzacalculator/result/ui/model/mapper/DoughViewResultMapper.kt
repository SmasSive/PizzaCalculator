package com.smassive.pizzacalculator.result.ui.model.mapper

import com.smassive.pizzacalculator.common.ui.model.mapper.mapView
import com.smassive.pizzacalculator.domain.sourdough.model.SourdoughDoughResult
import com.smassive.pizzacalculator.domain.yeast.model.YeastDoughResult
import com.smassive.pizzacalculator.result.ui.model.DoughViewResult
import com.smassive.pizzacalculator.result.ui.model.IngredientValueType

class DoughViewResultMapper {

  suspend fun map(yeastDoughResult: YeastDoughResult) = mapView {
    with(yeastDoughResult) {
      DoughViewResult.Yeast(
        weight = weight,
        count = count,
        flour = DoughViewResult.IngredientView(
          name = flour.name,
          percentage = flour.percentage?.format(IngredientValueType.PERCENTAGE),
          quantity = flour.absolute.format(IngredientValueType.GRAMS),
        ),
        water = DoughViewResult.IngredientView(
          name = water.name,
          percentage = water.percentage?.format(IngredientValueType.PERCENTAGE),
          quantity = water.absolute.format(IngredientValueType.GRAMS),
        ),
        salt = DoughViewResult.IngredientView(
          name = salt.name,
          percentage = salt.percentage?.format(IngredientValueType.PERCENTAGE),
          quantity = salt.absolute.format(IngredientValueType.GRAMS),
        ),
        dryYeast = DoughViewResult.IngredientView(
          name = dryYeast.name,
          percentage = dryYeast.percentage?.format(IngredientValueType.PERCENTAGE),
          quantity = dryYeast.absolute.format(IngredientValueType.GRAMS),
        ),
        freshYeast = DoughViewResult.IngredientView(
          name = freshYeast.name,
          percentage = freshYeast.percentage?.format(IngredientValueType.PERCENTAGE),
          quantity = freshYeast.absolute.format(IngredientValueType.GRAMS),
        ),
      )
    }
  }

  suspend fun map(sourdoughDoughResult: SourdoughDoughResult) = mapView {
    with(sourdoughDoughResult) {
      DoughViewResult.Sourdough(
        weight = weight,
        count = count,
        overallFlour = DoughViewResult.IngredientView(
          name = overallFlour.name,
          percentage = overallFlour.percentage?.format(IngredientValueType.PERCENTAGE),
          quantity = overallFlour.absolute.format(IngredientValueType.GRAMS),
        ),
        requiredFlour = DoughViewResult.IngredientView(
          name = requiredFlour.name,
          percentage = requiredFlour.percentage?.format(IngredientValueType.PERCENTAGE),
          quantity = requiredFlour.absolute.format(IngredientValueType.GRAMS),
        ),
        sourdough = DoughViewResult.Sourdough.SourdoughView(
          total = DoughViewResult.IngredientView(
            name = sourdough.total.name,
            percentage = sourdough.total.percentage?.format(IngredientValueType.PERCENTAGE),
            quantity = sourdough.total.absolute.format(IngredientValueType.GRAMS),
          ),
          water = DoughViewResult.IngredientView(
            name = sourdough.water.name,
            percentage = sourdough.water.percentage?.format(IngredientValueType.PERCENTAGE),
            quantity = sourdough.water.absolute.format(IngredientValueType.GRAMS),
          ),
          flour = DoughViewResult.IngredientView(
            name = sourdough.flour.name,
            percentage = sourdough.flour.percentage?.format(IngredientValueType.PERCENTAGE),
            quantity = sourdough.flour.absolute.format(IngredientValueType.GRAMS),
          ),
        ),
        water = DoughViewResult.IngredientView(
          name = water.name,
          percentage = water.percentage?.format(IngredientValueType.PERCENTAGE),
          quantity = water.absolute.format(IngredientValueType.GRAMS),
        ),
        salt = DoughViewResult.IngredientView(
          name = salt.name,
          percentage = salt.percentage?.format(IngredientValueType.PERCENTAGE),
          quantity = salt.absolute.format(IngredientValueType.GRAMS),
        ),
      )
    }
  }
}
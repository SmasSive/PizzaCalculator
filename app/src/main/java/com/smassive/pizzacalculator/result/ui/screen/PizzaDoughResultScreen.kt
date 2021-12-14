package com.smassive.pizzacalculator.result.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smassive.pizzacalculator.R
import com.smassive.pizzacalculator.common.ui.screen.LoadingScreen
import com.smassive.pizzacalculator.config.ui.model.DoughViewType
import com.smassive.pizzacalculator.result.ui.model.DoughViewResult
import com.smassive.pizzacalculator.result.ui.viewmodel.ResultViewModel
import com.smassive.pizzacalculator.ui.theme.PizzaCalculatorTheme

@Composable
fun PizzaDoughResultScreen(
  resultViewModel: ResultViewModel,
  doughViewType: DoughViewType,
  count: Int,
  weight: Int,
  modifier: Modifier = Modifier,
  onClose: () -> Unit,
) {
  val doughResultState by remember(resultViewModel) {
    when (doughViewType) {
      DoughViewType.YEAST -> resultViewModel.onYeastRequested(weight, count)
      DoughViewType.SOURDOUGH -> resultViewModel.onSourdoughRequested(weight, count)
    }
  }.collectAsState(ResultViewModel.State.Calculating)

  PizzaDoughResultContent(modifier) {
    when (doughResultState) {
      ResultViewModel.State.Calculating -> {
        LoadingScreen(
          modifier = modifier,
          backgroundColor = MaterialTheme.colors.primary,
          progressColor = MaterialTheme.colors.onPrimary,
        )
      }
      is ResultViewModel.State.Calculated -> {
        PizzaDoughCalculatedScreen(
          doughResult = (doughResultState as ResultViewModel.State.Calculated).doughResult,
          modifier = modifier.align(Alignment.End),
          onClose = onClose,
        )
      }
    }
  }
}

@Composable
fun PizzaDoughResultContent(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
  val scrollState = rememberScrollState()
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp)
      .verticalScroll(scrollState),
  ) {
    content()
  }
}

@Composable
fun PizzaDoughCalculatedScreen(doughResult: DoughViewResult, modifier: Modifier = Modifier, onClose: () -> Unit = {}) {
  Text(
    text = "X",
    modifier = modifier
      .clickable { onClose() },
    style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onPrimary)
  )
  Spacer(modifier = Modifier.height(16.dp))
  Text(
    text = "Required ingredients for: ${doughResult.count} pizzas",
    style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onPrimary)
  )
  Spacer(modifier = Modifier.height(8.dp))
  Text(
    text = "Desired final weight per pizza: ${doughResult.weight} g",
    style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.onPrimary)
  )
  Spacer(modifier = Modifier.height(24.dp))
  when (doughResult) {
    is DoughViewResult.Sourdough -> SourdoughPizzaIngredientsScreen(sourdoughDoughViewResult = doughResult)
    is DoughViewResult.Yeast -> YeastPizzaIngredientsScreen(yeastDoughViewResult = doughResult)
  }
}

@Composable
fun YeastPizzaIngredientsScreen(yeastDoughViewResult: DoughViewResult.Yeast) {
  IngredientRow(
    ingredientName = yeastDoughViewResult.flour.name,
    ingredientPercentage = yeastDoughViewResult.flour.percentage,
    ingredientQuantity = yeastDoughViewResult.flour.quantity,
  )
  Spacer(modifier = Modifier.height(12.dp))
  Divider(color = MaterialTheme.colors.onPrimary, thickness = 2.dp)
  Spacer(modifier = Modifier.height(12.dp))
  IngredientRow(
    ingredientName = yeastDoughViewResult.water.name,
    ingredientPercentage = yeastDoughViewResult.water.percentage,
    ingredientQuantity = yeastDoughViewResult.water.quantity,
  )
  Spacer(modifier = Modifier.height(12.dp))
  Divider(color = MaterialTheme.colors.onPrimary, thickness = 2.dp)
  Spacer(modifier = Modifier.height(12.dp))
  IngredientRow(
    ingredientName = yeastDoughViewResult.salt.name,
    ingredientPercentage = yeastDoughViewResult.salt.percentage,
    ingredientQuantity = yeastDoughViewResult.salt.quantity,
  )
  Spacer(modifier = Modifier.height(12.dp))
  Divider(color = MaterialTheme.colors.onPrimary, thickness = 2.dp)
  Spacer(modifier = Modifier.height(12.dp))
  IngredientRow(
    ingredientName = yeastDoughViewResult.dryYeast.name,
    ingredientPercentage = yeastDoughViewResult.dryYeast.percentage,
    ingredientQuantity = yeastDoughViewResult.dryYeast.quantity,
  )
  Spacer(modifier = Modifier.height(12.dp))
  Divider(color = MaterialTheme.colors.onPrimary, thickness = 2.dp)
  Spacer(modifier = Modifier.height(12.dp))
  IngredientRow(
    ingredientName = yeastDoughViewResult.freshYeast.name,
    ingredientPercentage = yeastDoughViewResult.freshYeast.percentage,
    ingredientQuantity = yeastDoughViewResult.freshYeast.quantity,
  )
}

@Composable
fun SourdoughPizzaIngredientsScreen(sourdoughDoughViewResult: DoughViewResult.Sourdough) {
  IngredientRow(
    ingredientName = sourdoughDoughViewResult.overallFlour.name,
    ingredientPercentage = sourdoughDoughViewResult.overallFlour.percentage,
    ingredientQuantity = sourdoughDoughViewResult.overallFlour.quantity,
  )
  Spacer(modifier = Modifier.height(4.dp))
  Text(
    text = stringResource(id = R.string.sourdough_overall_flour_desc),
    style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.onPrimary)
  )
  Spacer(modifier = Modifier.height(12.dp))
  DashedDivider()
  Spacer(modifier = Modifier.height(12.dp))
  IngredientRow(
    ingredientName = sourdoughDoughViewResult.requiredFlour.name,
    ingredientPercentage = sourdoughDoughViewResult.requiredFlour.percentage,
    ingredientQuantity = sourdoughDoughViewResult.requiredFlour.quantity,
  )
  Spacer(modifier = Modifier.height(4.dp))
  Text(
    text = stringResource(id = R.string.sourdough_required_flour_desc),
    style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.onPrimary)
  )
  Spacer(modifier = Modifier.height(12.dp))
  Divider(color = MaterialTheme.colors.onPrimary, thickness = 2.dp)
  Spacer(modifier = Modifier.height(12.dp))
  IngredientRow(
    ingredientName = sourdoughDoughViewResult.sourdough.total.name,
    ingredientPercentage = sourdoughDoughViewResult.sourdough.total.percentage,
    ingredientQuantity = sourdoughDoughViewResult.sourdough.total.quantity,
  )
  Spacer(modifier = Modifier.height(12.dp))
  IngredientRow(
    ingredientName = sourdoughDoughViewResult.sourdough.water.name,
    ingredientPercentage = sourdoughDoughViewResult.sourdough.water.percentage,
    ingredientQuantity = sourdoughDoughViewResult.sourdough.water.quantity,
  )
  Spacer(modifier = Modifier.height(12.dp))
  IngredientRow(
    ingredientName = sourdoughDoughViewResult.sourdough.flour.name,
    ingredientPercentage = sourdoughDoughViewResult.sourdough.flour.percentage,
    ingredientQuantity = sourdoughDoughViewResult.sourdough.flour.quantity,
  )
  Spacer(modifier = Modifier.height(12.dp))
  Divider(color = MaterialTheme.colors.onPrimary, thickness = 2.dp)
  Spacer(modifier = Modifier.height(12.dp))
  IngredientRow(
    ingredientName = sourdoughDoughViewResult.water.name,
    ingredientPercentage = sourdoughDoughViewResult.water.percentage,
    ingredientQuantity = sourdoughDoughViewResult.water.quantity,
  )
  Spacer(modifier = Modifier.height(12.dp))
  Divider(color = MaterialTheme.colors.onPrimary, thickness = 2.dp)
  Spacer(modifier = Modifier.height(12.dp))
  IngredientRow(
    ingredientName = sourdoughDoughViewResult.salt.name,
    ingredientPercentage = sourdoughDoughViewResult.salt.percentage,
    ingredientQuantity = sourdoughDoughViewResult.salt.quantity,
  )
}

@Composable
fun IngredientRow(
  ingredientName: String,
  ingredientPercentage: String?,
  ingredientQuantity: String,
) {
  Row(modifier = Modifier) {
    Text(
      text = ingredientName,
      style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onPrimary),
      modifier = Modifier.weight(0.4f)
    )
    if (ingredientPercentage == null) {
      Spacer(modifier = Modifier.weight(0.3f))
    } else {
      Text(
        text = ingredientPercentage,
        style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onPrimary, textAlign = TextAlign.End),
        modifier = Modifier.weight(0.3f),
      )
    }
    Text(
      text = ingredientQuantity,
      style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onPrimary, textAlign = TextAlign.End),
      modifier = Modifier.weight(0.3f)
    )
  }
}

@Composable
fun DashedDivider(
  color: Color = MaterialTheme.colors.onPrimary,
) {
  val pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f)
  Canvas(Modifier
    .fillMaxWidth()
    .height(1.dp)) {
    drawLine(
      color = color,
      start = Offset(0f, 0f),
      end = Offset(size.width, 0f),
      pathEffect = pathEffect,
      strokeWidth = 8f,
    )
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  PizzaCalculatorTheme {
    PizzaDoughResultContent(Modifier.background(MaterialTheme.colors.primary)) {
      PizzaDoughCalculatedScreen(
//        doughResult = DoughViewResult.Yeast(
//          weight = 200,
//          count = 2,
//          flour = DoughViewResult.IngredientView("Flour", "100.00%", "246.91 g"),
//          water = DoughViewResult.IngredientView("Water", "60.00%", "148.15 g"),
//          salt = DoughViewResult.IngredientView("Salt", "2.00%", "4.94 g"),
//          dryYeast = DoughViewResult.IngredientView("Dry Yeast", "0.05%", "0.12 g"),
//          freshYeast = DoughViewResult.IngredientView("Fresh Yeast", "0.15%", "0.37 g"),
//        ),
        doughResult = DoughViewResult.Sourdough(
          weight = 200,
          count = 2,
          overallFlour = DoughViewResult.IngredientView("Overall flour", "100.00%", "246.91 g"),
          requiredFlour = DoughViewResult.IngredientView("Required flour", null, "222.22 g"),
          sourdough = DoughViewResult.Sourdough.SourdoughView(
            total = DoughViewResult.IngredientView("Sourdough", "20.00%", "49.38 g"),
            water = DoughViewResult.IngredientView("...Water", "10.00%", "24.69 g"),
            flour = DoughViewResult.IngredientView("...Flour", "10.00%", "24.69 g"),
          ),
          water = DoughViewResult.IngredientView("Water", "60.00%", "123.46 g"),
          salt = DoughViewResult.IngredientView("Salt", "2.00%", "4.94 g"),
        ),
      )
//      LoadingScreen()
    }
  }
}
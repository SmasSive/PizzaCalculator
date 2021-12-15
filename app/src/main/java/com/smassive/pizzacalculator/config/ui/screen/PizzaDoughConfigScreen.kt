package com.smassive.pizzacalculator.config.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smassive.pizzacalculator.R
import com.smassive.pizzacalculator.ui.theme.PizzaCalculatorTheme

@Composable
fun PizzaDoughConfigScreen(
  numberOfPizzas: String,
  weightPerPizza: String,
  onNumberOfPizzasChanged: (String) -> Unit,
  onWeightPerPizzaChanged: (String) -> Unit,
  onYeastRequested: (count: String, weight: String) -> Unit,
  onSourdoughRequested: (count: String, weight: String) -> Unit,
  isValid: Boolean = true,
) {
  val scrollState = rememberScrollState()
  val focusManager = LocalFocusManager.current

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
      .verticalScroll(scrollState),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Image(
      painter = painterResource(id = R.drawable.logo),
      contentDescription = "Pizza Dough Logo",
      modifier = Modifier.size(120.dp)
    )
    Text(
      text = "CALCULATOR",
      style = MaterialTheme.typography.h5,
      fontWeight = FontWeight.ExtraBold,
    )
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedNumberField(
      value = numberOfPizzas,
      onValueChange = onNumberOfPizzasChanged,
      label = "Number of pizzas",
      focusManager = focusManager,
    )
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedNumberField(
      value = weightPerPizza,
      onValueChange = onWeightPerPizzaChanged,
      label = "Weight per pizza (grams)",
      focusManager = focusManager,
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
      text = "1) Pick the amount of pizzas you want to bake and your desired final dough weight.",
      style = MaterialTheme.typography.caption
    )
    Text(
      text = "2) Choose between a yeast based dough or sourdough based dough.",
      style = MaterialTheme.typography.caption
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(
      verticalAlignment = Alignment.CenterVertically,

      modifier = Modifier.fillMaxWidth(),
    ) {
      Button(
        onClick = {
          focusManager.clearFocus()
          onYeastRequested(numberOfPizzas, weightPerPizza)
        },
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f),
        enabled = isValid,
      ) {
        Text(text = "Yeast")
      }
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = "- or -",
        modifier = Modifier
          .fillMaxWidth()
          .weight(0.3f),
        textAlign = TextAlign.Center,
      )
      Spacer(modifier = Modifier.width(8.dp))
      Button(
        onClick = {
          focusManager.clearFocus()
          onSourdoughRequested(numberOfPizzas, weightPerPizza)
        },
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f),
        enabled = isValid,
      ) {
        Text(text = "Sourdough")
      }
    }
    Spacer(modifier = Modifier.height(48.dp))
    Text(
      text = "Background",
      style = MaterialTheme.typography.h4,
      modifier = Modifier.align(Alignment.Start)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
      text = stringResource(id = R.string.background_desc_1),
      style = MaterialTheme.typography.body1
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = stringResource(id = R.string.background_desc_2),
      style = MaterialTheme.typography.body1
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = stringResource(id = R.string.background_desc_3),
      style = MaterialTheme.typography.body1
    )
  }
}

@Composable
fun OutlinedNumberField(
  value: String,
  label: String,
  onValueChange: (String) -> Unit,
  focusManager: FocusManager,
) {
  OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    label = {
      Text(text = label)
    },
    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
    modifier = Modifier.fillMaxWidth(),
  )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  PizzaCalculatorTheme {
    PizzaDoughConfigScreen(
      numberOfPizzas = "2",
      weightPerPizza = "200",
      onNumberOfPizzasChanged = {},
      onWeightPerPizzaChanged = {},
      onYeastRequested = { _, _ -> },
      onSourdoughRequested = { _, _ -> },
    )
  }
}
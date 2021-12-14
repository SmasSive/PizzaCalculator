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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.smassive.pizzacalculator.R
import com.smassive.pizzacalculator.ui.theme.PizzaCalculatorTheme

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PizzaDoughConfigScreen(
  count: Int,
  weight: Int,
  onYeastRequested: (count: Int, weight: Int) -> Unit,
  onSourdoughRequested: (count: Int, weight: Int) -> Unit,
) {
  val imageLoader = ImageLoader.Builder(LocalContext.current)
    .componentRegistry {
      add(SvgDecoder(LocalContext.current))
    }
    .build()
  val scrollState = rememberScrollState()

  val TextFieldSaver = run {
    mapSaver(
      save = { mapOf("text" to it.text, "index" to it.selection.end) },
      restore = { TextFieldValue(it["text"] as String, TextRange(it["index"] as Int)) },
    )
  }
  val numberOfPizzas = rememberSaveable(stateSaver = TextFieldSaver) {
    mutableStateOf(TextFieldValue(
      text = count.toString(),
      selection = TextRange(count.toString().length)
    ))
  }
  val weightPerPizza = rememberSaveable(stateSaver = TextFieldSaver) {
    mutableStateOf(TextFieldValue(
      text = weight.toString(),
      selection = TextRange(weight.toString().length)
    ))
  }
  val focusManager = LocalFocusManager.current

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
      .verticalScroll(scrollState),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CompositionLocalProvider(LocalImageLoader provides imageLoader) {
      Image(
        painter = rememberImagePainter(
          data = "https://raw.githubusercontent.com/hendricius/pizza-dough/master/calculator/src/images/logo.svg",
          imageLoader = LocalImageLoader.current,
          builder = {
            placeholder(0)
          }
        ),
        contentDescription = "Pizza Dough Logo",
        modifier = Modifier.size(120.dp)
      )
      Image(
        painter = rememberImagePainter(
          data = "https://raw.githubusercontent.com/hendricius/pizza-dough/master/calculator/src/images/calculator.svg",
          imageLoader = LocalImageLoader.current,
          builder = {
            placeholder(0)
          }
        ),
        contentDescription = "Pizza Dough Calculator",
        modifier = Modifier.size(150.dp, 40.dp)
      )
    }
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
      value = numberOfPizzas.value,
      onValueChange = { numberOfPizzas.value = it },
      label = {
        Text(text = "Number of pizzas")
      },
      keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
      keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
    )
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
      value = weightPerPizza.value,
      onValueChange = { weightPerPizza.value = it },
      label = {
        Text(text = "Weight per pizza (grams)")
      },
      keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
      keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
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
    Row(verticalAlignment = Alignment.CenterVertically) {
      Button(onClick = {
        focusManager.clearFocus()
        onYeastRequested(numberOfPizzas.value.text.toInt(), weightPerPizza.value.text.toInt())
      }) {
        Text(text = "Yeast")
      }
      Spacer(modifier = Modifier.width(8.dp))
      Text(text = "- or -")
      Spacer(modifier = Modifier.width(8.dp))
      Button(onClick = {
        focusManager.clearFocus()
        onSourdoughRequested(numberOfPizzas.value.text.toInt(), weightPerPizza.value.text.toInt())
      }) {
        Text(text = "Sourdough")
      }
    }
    Spacer(modifier = Modifier.height(24.dp))
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  PizzaCalculatorTheme {
    PizzaDoughConfigScreen(
      count = 2,
      weight = 200,
      onYeastRequested = { _,_ -> },
      onSourdoughRequested = { _,_ -> },
    )
  }
}
package com.smassive.pizzacalculator.common.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.smassive.pizzacalculator.ui.theme.PizzaCalculatorTheme

@Composable
fun LoadingScreen(
  modifier: Modifier = Modifier,
  backgroundColor: Color = MaterialTheme.colors.background,
  progressColor: Color = MaterialTheme.colors.primary,
) {
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(backgroundColor),
    contentAlignment = Alignment.Center
  ) {
    CircularProgressIndicator(modifier = modifier, color = progressColor)
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  PizzaCalculatorTheme {
    LoadingScreen(
      modifier = Modifier,
      backgroundColor = MaterialTheme.colors.primary,
      progressColor = MaterialTheme.colors.onPrimary
    )
  }
}
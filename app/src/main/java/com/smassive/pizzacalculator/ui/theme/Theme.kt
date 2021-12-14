package com.smassive.pizzacalculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
  primary = MatureTomato,
  primaryVariant = MatureTomato,
  secondary = MatureTomato,
  background = Blackish,
  surface = Blackish,
  onPrimary = Blackish,
  onSecondary = Blackish,
  onBackground = Color.LightGray,
  onSurface = Color.LightGray,
)

private val LightColorPalette = lightColors(
  primary = Tomato,
  primaryVariant = Tomato,
  secondary = Tomato,
  background = Color.White,
  surface = Color.White,
  onPrimary = Color.White,
  onSecondary = Color.White,
  onBackground = Color.Black,
  onSurface = Color.Black,
)

@Composable
fun PizzaCalculatorTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}

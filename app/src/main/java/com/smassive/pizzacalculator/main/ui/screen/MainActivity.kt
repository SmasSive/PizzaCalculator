package com.smassive.pizzacalculator.main.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smassive.pizzacalculator.common.ui.screen.LoadingScreen
import com.smassive.pizzacalculator.config.ui.model.DoughViewType
import com.smassive.pizzacalculator.config.ui.screen.PizzaDoughConfigScreen
import com.smassive.pizzacalculator.main.ui.viewmodel.MainViewModel
import com.smassive.pizzacalculator.result.ui.screen.PizzaDoughResultScreen
import com.smassive.pizzacalculator.result.ui.viewmodel.ResultViewModel
import com.smassive.pizzacalculator.ui.PizzaCalculatorScreens
import com.smassive.pizzacalculator.ui.theme.PizzaCalculatorTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

  private val mainViewModel: MainViewModel by viewModel()
  private val resultViewModel: ResultViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      val navController = rememberNavController()
      PizzaCalculatorTheme {
        Scaffold { innerPadding ->
          Surface(color = MaterialTheme.colors.background) {
            NavHost(navController = navController,
              startDestination = PizzaCalculatorScreens.ConfigScreen.name,
              modifier = Modifier.padding(innerPadding)
            ) {
              composable(PizzaCalculatorScreens.ConfigScreen.name) {
                MainActivityScreen(mainViewModel) { doughType, count, weight ->
                  navController.navigate(PizzaCalculatorScreens.ResultScreen.name + "/$doughType/$count/$weight")
                }
              }
              composable(PizzaCalculatorScreens.ResultScreen.name + "/{doughType}/{count}/{weight}") { backStackEntry ->
                backStackEntry.arguments?.let {
                  PizzaDoughResultScreen(
                    resultViewModel = resultViewModel,
                    doughViewType = DoughViewType.valueOf(it.getString("doughType", "YEAST")),
                    count = it.getString("count", "0").toInt(),
                    weight = it.getString("weight", "0").toInt(),
                    modifier = Modifier.background(MaterialTheme.colors.primary)
                  ) {
                    navController.popBackStack()
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun MainActivityScreen(mainViewModel: MainViewModel, onDoughCalculationRequested: (DoughViewType, Int, Int) -> Unit = { _, _, _ -> }) {
  val state by remember(mainViewModel) {
    mainViewModel.getDoughConfig()
  }.collectAsState(MainViewModel.State.Loading)

  when (state) {
    is MainViewModel.State.Success -> {
      val successState = state as MainViewModel.State.Success
      PizzaDoughConfigScreen(
        count = successState.doughConfig.count,
        weight = successState.doughConfig.weight,
        onYeastRequested = { count, weight ->
          // TODO feature -> save last config?
          onDoughCalculationRequested(DoughViewType.YEAST, count, weight)
        },
        onSourdoughRequested = { count, weight ->
          // TODO feature -> save last config?
          onDoughCalculationRequested(DoughViewType.SOURDOUGH, count, weight)
        },
      )
    }
    MainViewModel.State.Loading -> {
      LoadingScreen()
      // TODO recover last saved config
      mainViewModel.getDoughConfig()
    }
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  PizzaCalculatorTheme {
    MainActivityScreen(MainViewModel())
  }
}
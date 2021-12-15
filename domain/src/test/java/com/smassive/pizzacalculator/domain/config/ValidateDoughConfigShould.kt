package com.smassive.pizzacalculator.domain.config

import com.smassive.pizzacalculator.domain.core.UnitTest
import com.smassive.pizzacalculator.domain.core.runBlockingTest
import com.smassive.pizzacalculator.domain.di.domainModule
import org.junit.Test
import org.koin.core.module.Module
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidateDoughConfigShould : UnitTest() {

  private val validateDoughConfig: ValidateDoughConfig by inject()

  override val modules: List<Module> = listOf(domainModule)

  @Test
  fun `return true when number of pizzas and weight per pizza are valid numbers`() = runBlockingTest {
    val validNumberOfPizzas = "2"
    val validWeightPerPizza = "200"

    val isValid = validateDoughConfig(validNumberOfPizzas, validWeightPerPizza)

    assertTrue(isValid)
  }

  @Test
  fun `return false when number of pizzas is empty`() = runBlockingTest {
    val emptyNumberOfPizzas = ""
    val validWeightPerPizza = "200"

    val isValid = validateDoughConfig(emptyNumberOfPizzas, validWeightPerPizza)

    assertFalse(isValid)
  }

  @Test
  fun `return false when weight per pizza is empty`() = runBlockingTest {
    val validNumberOfPizzas = "2"
    val emptyWeightPerPizza = ""

    val isValid = validateDoughConfig(validNumberOfPizzas, emptyWeightPerPizza)

    assertFalse(isValid)
  }

  @Test
  fun `return false when number of pizzas is not a valid number`() = runBlockingTest {
    val notANumberOfPizzas = "-"
    val validWeightPerPizza = "200"

    val isValid = validateDoughConfig(notANumberOfPizzas, validWeightPerPizza)

    assertFalse(isValid)
  }

  @Test
  fun `return false when weight per pizza is not a valid number`() = runBlockingTest {
    val validNumberOfPizzas = "2"
    val notAWeightPerPizza = "-"

    val isValid = validateDoughConfig(validNumberOfPizzas, notAWeightPerPizza)

    assertFalse(isValid)
  }
}
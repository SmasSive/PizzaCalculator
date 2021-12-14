package com.smassive.pizzacalculator.domain.yeast

import com.smassive.pizzacalculator.domain.core.UnitTest
import com.smassive.pizzacalculator.domain.core.runBlockingTest
import com.smassive.pizzacalculator.domain.di.domainModule
import com.smassive.pizzacalculator.domain.model.DoughConfig
import com.smassive.pizzacalculator.domain.model.DoughRequestModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.core.module.Module
import org.koin.test.inject

class CalculateYeastDoughShould : UnitTest() {

  private val calculateYeastDough: CalculateYeastDough by inject()

  override val modules: List<Module> = listOf(domainModule)

  @Test
  fun `return '246,91 gr' of total flour for two pizzas of '200 gr' each`() = runBlockingTest {

    val doughResult = calculateYeastDough(DoughRequestModel.Yeast(DoughConfig()))

    assertEquals(246.91, doughResult.flour.absolute, 0.001)
  }

  @Test
  fun `return '148,15 gr' of total water for two pizzas of '200 gr' each`() = runBlockingTest {

    val doughResult = calculateYeastDough(DoughRequestModel.Yeast(DoughConfig()))

    assertEquals(148.15, doughResult.water.absolute, 0.001)
  }

  @Test
  fun `return '4,94 gr' of total salt for two pizzas of '200 gr' each`() = runBlockingTest {

    val doughResult = calculateYeastDough(DoughRequestModel.Yeast(DoughConfig()))

    assertEquals(4.94, doughResult.salt.absolute, 0.001)
  }

  @Test
  fun `return '0,12 gr' of total dry yeast for two pizzas of '200 gr' each`() = runBlockingTest {

    val doughResult = calculateYeastDough(DoughRequestModel.Yeast(DoughConfig()))

    assertEquals(0.12, doughResult.dryYeast.absolute, 0.001)
  }

  @Test
  fun `return '0,37 gr' of total fresh yeast for two pizzas of '200 gr' each`() = runBlockingTest {

    val doughResult = calculateYeastDough(DoughRequestModel.Yeast(DoughConfig()))

    assertEquals(0.37, doughResult.freshYeast.absolute, 0.001)
  }

  @Test
  fun `return the calculated ingredients for a 65 percent of hydration`() = runBlockingTest {
    val sixtyHydrationDough = DoughRequestModel.Yeast(DoughConfig(water = 0.65))

    val doughResult = calculateYeastDough(sixtyHydrationDough)

    assertEquals(239.52, doughResult.flour.absolute, 0.001)
    assertEquals(155.69, doughResult.water.absolute, 0.001)
    assertEquals(4.79, doughResult.salt.absolute, 0.001)
    assertEquals(0.12, doughResult.dryYeast.absolute, 0.001)
    assertEquals(0.36, doughResult.freshYeast.absolute, 0.001)
  }
}
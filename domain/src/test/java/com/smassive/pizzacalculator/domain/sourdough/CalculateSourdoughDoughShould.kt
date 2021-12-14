package com.smassive.pizzacalculator.domain.sourdough

import com.smassive.pizzacalculator.domain.core.UnitTest
import com.smassive.pizzacalculator.domain.core.runBlockingTest
import com.smassive.pizzacalculator.domain.di.domainModule
import com.smassive.pizzacalculator.domain.model.DoughConfig
import com.smassive.pizzacalculator.domain.model.DoughRequestModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.core.module.Module
import org.koin.test.inject

class CalculateSourdoughDoughShould : UnitTest() {

  private val calculateSourdoughDough: CalculateSourdoughDough by inject()

  override val modules: List<Module> = listOf(domainModule)

  @Test
  fun `return '246,91 gr' of total flour for two pizzas of '200 gr' each`() = runBlockingTest {

    val doughResult = calculateSourdoughDough(DoughRequestModel.Sourdough(DoughConfig()))

    assertEquals(246.91, doughResult.overallFlour.absolute, 0.001)
  }

  @Test
  fun `return '222,22 gr' of required flour for two pizzas of '200 gr' each`() = runBlockingTest {

    val doughResult = calculateSourdoughDough(DoughRequestModel.Sourdough(DoughConfig()))

    assertEquals(222.22, doughResult.requiredFlour.absolute, 0.001)
  }

  @Test
  fun `return '49,38 gr' of sourdough for two pizzas of '200 gr' each`() = runBlockingTest {

    val doughResult = calculateSourdoughDough(DoughRequestModel.Sourdough(DoughConfig()))

    assertEquals(49.38, doughResult.sourdough.total.absolute, 0.001)
  }

  @Test
  fun `return '20 percent' of sourdough for two pizzas of '200 gr' each`() = runBlockingTest {

    val doughResult = calculateSourdoughDough(DoughRequestModel.Sourdough(DoughConfig()))

    assertEquals(0.2, doughResult.sourdough.total.percentage!!, 0.001)
  }

  @Test
  fun `return '24,69 gr' of water from sourdough for two pizzas of '200 gr' each`() = runBlockingTest {

    val doughResult = calculateSourdoughDough(DoughRequestModel.Sourdough(DoughConfig()))

    assertEquals(24.69, doughResult.sourdough.water.absolute, 0.001)
  }

  @Test
  fun `return '10 percent' of water from sourdough for two pizzas of '200 gr' each`() = runBlockingTest {

    val doughResult = calculateSourdoughDough(DoughRequestModel.Sourdough(DoughConfig()))

    assertEquals(0.1, doughResult.sourdough.water.percentage!!, 0.001)
  }

  @Test
  fun `return '24,69 gr' of flour from sourdough for two pizzas of '200 gr' each`() = runBlockingTest {

    val doughResult = calculateSourdoughDough(DoughRequestModel.Sourdough(DoughConfig()))

    assertEquals(24.69, doughResult.sourdough.flour.absolute, 0.001)
  }

  @Test
  fun `return '10 percent' of flour from sourdough for two pizzas of '200 gr' each`() = runBlockingTest {

    val doughResult = calculateSourdoughDough(DoughRequestModel.Sourdough(DoughConfig()))

    assertEquals(0.1, doughResult.sourdough.flour.percentage!!, 0.001)
  }

  @Test
  fun `return '123,46 gr' of water for two pizzas of '200 gr' each`() = runBlockingTest {

    val doughResult = calculateSourdoughDough(DoughRequestModel.Sourdough(DoughConfig()))

    assertEquals(123.46, doughResult.water.absolute, 0.001)
  }

  @Test
  fun `return '4,94 gr' of salt for two pizzas of '200 gr' each`() = runBlockingTest {

    val doughResult = calculateSourdoughDough(DoughRequestModel.Sourdough(DoughConfig()))

    assertEquals(4.94, doughResult.salt.absolute, 0.001)
  }
}

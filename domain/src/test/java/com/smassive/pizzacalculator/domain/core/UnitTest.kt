package com.smassive.pizzacalculator.domain.core

import org.junit.Rule
import org.koin.core.module.Module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

abstract class UnitTest : KoinTest {

  @get:Rule
  val koinTestRule = KoinTestRule.create {
    printLogger()
    modules(modules)
  }

  abstract val modules: List<Module>
}
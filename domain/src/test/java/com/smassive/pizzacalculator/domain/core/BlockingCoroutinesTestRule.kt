package com.smassive.pizzacalculator.domain.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class BlockingCoroutinesTestRule : TestRule {
  override fun apply(base: Statement, description: Description): Statement {
    return object : Statement() {
      @Throws(Throwable::class)
      override fun evaluate() {
        applyTestDispatcher()

        base.evaluate()

        resetDispatchers()
      }
    }
  }

  private fun applyTestDispatcher() {
    val dispatcher = TestCoroutineDispatcher()
    Dispatchers.setMain(dispatcher)
    DispatchersProvider.mainReplacement = dispatcher
    DispatchersProvider.computationReplacement = dispatcher
    DispatchersProvider.ioReplacement = dispatcher
  }

  private fun resetDispatchers() {
    Dispatchers.resetMain()
    DispatchersProvider.mainReplacement = null
    DispatchersProvider.computationReplacement = null
    DispatchersProvider.ioReplacement = null
  }
}

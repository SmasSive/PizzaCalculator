package com.smassive.pizzacalculator.domain.core

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispatchersProvider {
  val main: CoroutineDispatcher
    get() = mainReplacement ?: Dispatchers.Main.immediate

  @VisibleForTesting
  var mainReplacement: CoroutineDispatcher? = null

  val computation: CoroutineDispatcher
    get() = computationReplacement ?: Dispatchers.Default

  @VisibleForTesting
  var computationReplacement: CoroutineDispatcher? = null

  val io: CoroutineDispatcher
    get() = ioReplacement ?: Dispatchers.IO

  @VisibleForTesting
  var ioReplacement: CoroutineDispatcher? = null
}

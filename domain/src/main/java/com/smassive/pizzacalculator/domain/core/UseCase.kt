package com.smassive.pizzacalculator.domain.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

abstract class UseCase {

  suspend fun <R> execute(code: suspend CoroutineScope.() -> R): R {
    return withContext(DispatchersProvider.computation) { code() }
  }
}
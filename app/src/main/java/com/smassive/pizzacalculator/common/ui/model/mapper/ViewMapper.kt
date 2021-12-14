package com.smassive.pizzacalculator.common.ui.model.mapper

import com.smassive.pizzacalculator.domain.core.DispatchersProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

suspend fun <R> mapView(code: suspend CoroutineScope.() -> R): R {
  return withContext(DispatchersProvider.computation) { code() }
}
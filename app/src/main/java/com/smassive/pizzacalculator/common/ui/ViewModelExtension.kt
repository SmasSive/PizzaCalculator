package com.smassive.pizzacalculator.common.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

fun <T> ViewModel.flowOnViewModel(block: suspend FlowCollector<T>.() -> Unit) = flow {
  block()
}.shareIn(viewModelScope, SharingStarted.WhileSubscribed())
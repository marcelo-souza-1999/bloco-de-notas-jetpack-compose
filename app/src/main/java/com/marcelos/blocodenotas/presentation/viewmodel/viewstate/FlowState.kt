package com.marcelos.blocodenotas.presentation.viewmodel.viewstate

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart

internal suspend fun <T> Flow<T>.collectViewState(viewState: MutableStateFlow<State<T>>) =
    onStart {
        viewState.value = State.Loading()
    }.collect {
        viewState.value = State.Success(it)
    }
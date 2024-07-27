package com.marcelos.blocodenotas.presentation.viewmodel.viewstate

sealed interface State<T> {
    data class Success<T>(val data: T) : State<T>
    class Loading<T> : State<T>
}
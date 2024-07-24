package com.marcelos.blocodenotas.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelos.blocodenotas.domain.usecase.GetAnnotation
import com.marcelos.blocodenotas.domain.usecase.SaveAnnotation
import com.marcelos.blocodenotas.presentation.viewmodel.viewstate.State
import com.marcelos.blocodenotas.presentation.viewmodel.viewstate.collectViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val getAnnotationUseCase: GetAnnotation,
    private val saveAnnotationUseCase: SaveAnnotation
) : ViewModel() {

    private val _viewStateSaveAnnotation =
        MutableStateFlow<State<Unit>>(State.Loading())
    val viewStateSaveAnnotation = _viewStateSaveAnnotation.asStateFlow()

    private val _viewStateGetAnnotation =
        MutableStateFlow<State<String>>(State.Loading())
    val viewStateGetAnnotation = _viewStateGetAnnotation.asStateFlow()

    init {
        getAnnotationSaved()
    }

    fun saveAnnotation(annotation: String) = viewModelScope.launch {
        saveAnnotationUseCase(annotation).collectViewState(_viewStateSaveAnnotation)
    }

    private fun getAnnotationSaved() = viewModelScope.launch {
        getAnnotationUseCase().collectViewState(_viewStateGetAnnotation)
    }
}
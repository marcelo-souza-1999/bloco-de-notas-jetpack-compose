package com.marcelos.blocodenotas.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelos.blocodenotas.domain.usecase.GetAnnotationUseCase
import com.marcelos.blocodenotas.domain.usecase.SaveAnnotationUseCase
import com.marcelos.blocodenotas.presentation.viewmodel.viewstate.State
import com.marcelos.blocodenotas.presentation.viewmodel.viewstate.collectViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val getAnnotationUseCase: GetAnnotationUseCase,
    private val saveAnnotationUseCase: SaveAnnotationUseCase
) : ViewModel() {

    private val _viewStateSaveAnnotation =
        MutableStateFlow<State<Unit>>(State.Loading())
    val viewStateSaveAnnotation = _viewStateSaveAnnotation.asStateFlow()

    private val _viewStateGetAnnotation =
        MutableStateFlow<State<String>>(State.Loading())
    val viewStateGetAnnotation = _viewStateGetAnnotation.asStateFlow()

    private val _annotation = MutableStateFlow("")
    val annotation = _annotation.asStateFlow()

    init {
        getAnnotationSaved()
    }

    fun saveAnnotation(annotation: String) = viewModelScope.launch {
        saveAnnotationUseCase(annotation).collectViewState(_viewStateSaveAnnotation)
    }

    fun updateAnnotation(newAnnotation: String) {
        _annotation.value = newAnnotation
    }

    fun getAnnotationSaved() = viewModelScope.launch {
        getAnnotationUseCase().collectViewState(_viewStateGetAnnotation)
    }
}
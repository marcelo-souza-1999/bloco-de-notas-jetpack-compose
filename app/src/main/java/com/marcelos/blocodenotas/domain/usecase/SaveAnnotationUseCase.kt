package com.marcelos.blocodenotas.domain.usecase

import com.marcelos.blocodenotas.domain.repository.LocalStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Single

@Single
class SaveAnnotationUseCase(
    private val repository: LocalStorageRepository
) {
    suspend operator fun invoke(
        annotation: String
    ): Flow<Unit> {
        val result = repository.saveAnnotation(annotation)
        return flowOf(result)
    }
}

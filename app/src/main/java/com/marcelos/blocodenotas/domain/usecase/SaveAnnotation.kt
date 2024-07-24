package com.marcelos.blocodenotas.domain.usecase

import com.marcelos.blocodenotas.domain.repository.LocalStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Single

@Single
class SaveAnnotation(
    private val repository: LocalStorageRepository
) {
    suspend operator fun invoke(
        annotation: String
    ): Flow<Unit> = flow {
        emit(repository.saveAnnotation(annotation))
    }
}
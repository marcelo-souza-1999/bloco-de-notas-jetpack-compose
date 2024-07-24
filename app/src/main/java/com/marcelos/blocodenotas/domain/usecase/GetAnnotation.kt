package com.marcelos.blocodenotas.domain.usecase

import com.marcelos.blocodenotas.domain.repository.LocalStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Single

@Single
class GetAnnotation(
    private val repository: LocalStorageRepository
) {
    suspend operator fun invoke(): Flow<String> = flow {
        repository.key.collect { annotation ->
            emit(annotation)
        }
    }
}
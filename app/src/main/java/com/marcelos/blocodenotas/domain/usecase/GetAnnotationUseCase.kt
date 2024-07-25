package com.marcelos.blocodenotas.domain.usecase

import com.marcelos.blocodenotas.domain.repository.LocalStorageRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class GetAnnotationUseCase(
    private val repository: LocalStorageRepository
) {
    operator fun invoke(): Flow<String> = repository.key
}
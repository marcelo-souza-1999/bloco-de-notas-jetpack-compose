package com.marcelos.blocodenotas.local.repository

import com.marcelos.blocodenotas.domain.datasource.LocalStorageDataSource
import com.marcelos.blocodenotas.domain.repository.LocalStorageRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class LocalStorageRepositoryImpl(
    private val localStorageDataSource: LocalStorageDataSource
) : LocalStorageRepository {

    override val key: Flow<String> get() = localStorageDataSource.key

    override suspend fun saveAnnotation(annotation: String) =
        localStorageDataSource.saveAnnotation(annotation)
}
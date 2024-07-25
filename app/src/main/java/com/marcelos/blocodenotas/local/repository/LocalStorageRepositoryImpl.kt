package com.marcelos.blocodenotas.local.repository

import com.marcelos.blocodenotas.domain.datasource.LocalStorageDataSource
import com.marcelos.blocodenotas.domain.repository.LocalStorageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Single

@Single
class LocalStorageRepositoryImpl(
    private val localStorageDataSource: LocalStorageDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocalStorageRepository {

    override val key: Flow<String>
        get() = localStorageDataSource.key
            .flowOn(ioDispatcher)

    override suspend fun saveAnnotation(annotation: String) =
        localStorageDataSource.saveAnnotation(annotation)
}
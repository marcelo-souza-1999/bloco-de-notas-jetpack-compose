package com.marcelos.blocodenotas.domain.datasource

import kotlinx.coroutines.flow.Flow

interface LocalStorageDataSource {

    val key: Flow<String>

    suspend fun saveAnnotation(annotation: String)
}
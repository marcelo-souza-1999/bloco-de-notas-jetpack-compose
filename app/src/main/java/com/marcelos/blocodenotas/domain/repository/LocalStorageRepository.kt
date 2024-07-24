package com.marcelos.blocodenotas.domain.repository

import kotlinx.coroutines.flow.Flow

interface LocalStorageRepository {

    val key: Flow<String>

    suspend fun saveAnnotation(annotation: String)
}
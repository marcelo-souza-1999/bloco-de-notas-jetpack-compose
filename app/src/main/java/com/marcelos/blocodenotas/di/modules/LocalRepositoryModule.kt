package com.marcelos.blocodenotas.di.modules

import com.marcelos.blocodenotas.domain.datasource.LocalStorageDataSource
import com.marcelos.blocodenotas.domain.repository.LocalStorageRepository
import com.marcelos.blocodenotas.local.repository.LocalStorageRepositoryImpl
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@Single
class LocalRepositoryModule {

    @Single
    fun providesLocalStorageRepository(
        localStorageDataSource: LocalStorageDataSource
    ): LocalStorageRepository {
        return LocalStorageRepositoryImpl(localStorageDataSource)
    }
}
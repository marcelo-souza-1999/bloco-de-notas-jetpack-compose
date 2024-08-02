package com.marcelos.blocodenotas.local.repository

import com.marcelos.blocodenotas.domain.datasource.LocalStorageDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LocalStorageRepositoryImplTest {

    private lateinit var localStorageDataSource: LocalStorageDataSource
    private lateinit var localStorageRepository: LocalStorageRepositoryImpl

    @Before
    fun setUp() {
        localStorageDataSource = mockk()
        localStorageRepository = LocalStorageRepositoryImpl(localStorageDataSource, Dispatchers.Unconfined)
    }

    @Test
    fun `key should return data from dataSource with flowOn dispatcher`() = runTest {
        val expectedKey = "Sample Key"
        coEvery { localStorageDataSource.key } returns flowOf(expectedKey)

        val result = localStorageRepository.key.first()

        assertEquals(expectedKey, result)
    }

    @Test
    fun `saveAnnotation should call saveAnnotation on dataSource`() = runTest {
        val annotation = "Sample Annotation"
        coEvery { localStorageDataSource.saveAnnotation(annotation) } returns Unit

        localStorageRepository.saveAnnotation(annotation)

        coVerify { localStorageDataSource.saveAnnotation(annotation) }
    }
}

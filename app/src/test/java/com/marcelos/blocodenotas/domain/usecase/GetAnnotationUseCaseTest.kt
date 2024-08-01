package com.marcelos.blocodenotas.domain.usecase

import com.marcelos.blocodenotas.domain.repository.LocalStorageRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetAnnotationUseCaseTest {

    private lateinit var repository: LocalStorageRepository
    private lateinit var getAnnotationUseCase: GetAnnotationUseCase

    @Before
    fun setUp() {
        repository = mockk()
        getAnnotationUseCase = GetAnnotationUseCase(repository)
    }

    @Test
    fun `invoke should return annotation from repository`() = runTest {
        val annotation = "Sample Annotation"
        coEvery { repository.key } returns flowOf(annotation)

        val result = getAnnotationUseCase().first()
        assertEquals(annotation, result)
    }
}

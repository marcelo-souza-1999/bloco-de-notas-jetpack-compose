package com.marcelos.blocodenotas.domain.usecase

import com.marcelos.blocodenotas.domain.repository.LocalStorageRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveAnnotationUseCaseTest {

    private lateinit var repository: LocalStorageRepository
    private lateinit var saveAnnotationUseCase: SaveAnnotationUseCase

    @Before
    fun setUp() {
        repository = mockk()
        saveAnnotationUseCase = SaveAnnotationUseCase(repository)
    }

    @Test
    fun `invoke should save annotation and return flow of Unit`() = runTest {
        val annotation = "Sample Annotation"
        coEvery { repository.saveAnnotation(annotation) } returns Unit

        val result = saveAnnotationUseCase(annotation).first()
        assertEquals(Unit, result)
    }
}

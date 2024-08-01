package com.marcelos.blocodenotas.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marcelos.blocodenotas.domain.usecase.GetAnnotationUseCase
import com.marcelos.blocodenotas.domain.usecase.SaveAnnotationUseCase
import com.marcelos.blocodenotas.presentation.viewmodel.viewstate.State
import com.marcelos.blocodenotas.utils.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var getAnnotationUseCase: GetAnnotationUseCase
    private lateinit var saveAnnotationUseCase: SaveAnnotationUseCase
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        getAnnotationUseCase = mockk(relaxed = true)
        saveAnnotationUseCase = mockk(relaxed = true)
        mainViewModel = MainViewModel(getAnnotationUseCase, saveAnnotationUseCase)
    }

    @Test
    fun `getAnnotationSaved should update viewStateGetAnnotation with success state`() = runTest {
        val annotation = "Sample Annotation"
        coEvery { getAnnotationUseCase.invoke() } returns flowOf(annotation)

        mainViewModel.getAnnotationSaved()

        val result = mainViewModel.viewStateGetAnnotation.first()
        assert(result is State.Success)
        assertEquals((result as State.Success).data, annotation)
    }

    @Test
    fun `getAnnotationSaved should update viewStateGetAnnotation with loading state`() = runTest {
        coEvery { getAnnotationUseCase.invoke() } returns flowOf()

        mainViewModel.getAnnotationSaved()

        val result = mainViewModel.viewStateGetAnnotation.first()
        assertTrue(result is State.Loading)
    }

    @Test
    fun `saveAnnotation should update viewStateSaveAnnotation with success state`() = runTest {
        val annotation = "Sample Annotation"
        coEvery { saveAnnotationUseCase.invoke(annotation) } returns flowOf(Unit)

        mainViewModel.saveAnnotation(annotation)

        val result = mainViewModel.viewStateSaveAnnotation.first()
        assert(result is State.Success)
    }

    @Test
    fun `saveAnnotation should update viewStateSaveAnnotation with loading state`() = runTest {
        val annotation = "Sample Annotation"
        coEvery { saveAnnotationUseCase.invoke(annotation) } returns flowOf()

        mainViewModel.saveAnnotation(annotation)

        val result = mainViewModel.viewStateSaveAnnotation.first()
        assertTrue(result is State.Loading)
    }

    @Test
    fun `updateAnnotation should update annotation state`() = runTest {
        val newAnnotation = "Updated Annotation"
        mainViewModel.updateAnnotation(newAnnotation)

        val result = mainViewModel.annotation.first()
        assertEquals(result, newAnnotation)
    }
}

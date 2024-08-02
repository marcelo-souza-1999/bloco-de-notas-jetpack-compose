package com.marcelos.blocodenotas.presentation.ui.activity

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marcelos.blocodenotas.presentation.viewmodel.MainViewModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import kotlin.test.Test

@RunWith(AndroidJUnit4::class)
class MainActivityTest : KoinTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        if (GlobalContext.getOrNull() == null) {
            startKoin {
                modules(
                    module {
                        viewModel { MainViewModel(get(), get()) }
                    }
                )
            }
        }

        ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun checkAllElementsAreDisplayed() {
        composeTestRule.onNodeWithTag("topBar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("txtInsertAnnotation").assertIsDisplayed()
    }

    @Test
    fun checkTopBarTitleIsCorrect() {
        composeTestRule
            .onNodeWithTag("topBar")
            .onChildren()
            .filter(hasText("Bloco de Notas"))
    }

    @Test
    fun checkFloatingActionButtonIsDisplayed() {
        composeTestRule.onNodeWithTag("floatingActionBtn").assertIsDisplayed()
    }
}

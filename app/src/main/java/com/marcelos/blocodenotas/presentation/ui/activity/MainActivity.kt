package com.marcelos.blocodenotas.presentation.ui.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.marcelos.blocodenotas.R
import com.marcelos.blocodenotas.presentation.theme.Black
import com.marcelos.blocodenotas.presentation.theme.BlocoDeNotasTheme
import com.marcelos.blocodenotas.presentation.theme.TypographyTopBar
import com.marcelos.blocodenotas.presentation.theme.White
import com.marcelos.blocodenotas.presentation.theme.Yellow
import com.marcelos.blocodenotas.presentation.viewmodel.MainViewModel
import com.marcelos.blocodenotas.presentation.viewmodel.viewstate.State
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlocoDeNotasTheme {
                setupStatusBarNative()
                MainContent()
            }
        }
    }

    private fun setupStatusBarNative() = enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.dark(
            scrim = Black.toArgb()
        )
    )

    @Preview(showBackground = true, showSystemUi = false)
    @Composable
    private fun MainContent() {
        var annotation by remember { mutableStateOf("") }
        val viewStateGetAnnotation by viewModel.viewStateGetAnnotation.collectAsState()
        val viewStateSaveAnnotation by viewModel.viewStateSaveAnnotation.collectAsState()
        val focusRequester = remember { FocusRequester() }
        val context = LocalContext.current

        LaunchedEffect(viewStateGetAnnotation) {
            handleGetAnnotationSaved(viewStateGetAnnotation) { savedAnnotation ->
                annotation = savedAnnotation
                focusRequester.requestFocus()
            }
        }

        Scaffold(topBar = { CreateTopBar() },
            floatingActionButton = { CreateFloatingActionButton(annotation) }) { innerPadding ->
            Column(
                modifier = Modifier
                    .background(White)
                    .padding(innerPadding)
            ) {
                CreateTxtFieldInsertAnnotation(
                    value = annotation,
                    onValueChange = { annotation = it },
                    focusRequester = focusRequester,
                    showLabel = annotation.isEmpty()
                )
            }
        }

        handleSaveAnnotation(
            viewStateSaveAnnotation, context
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun CreateTopBar() {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.title_top_bar),
                    style = TypographyTopBar.bodyLarge,
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.size_8))
                )
            },
            modifier = Modifier.testTag("topBar"),
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = Yellow, titleContentColor = Black
            )
        )
    }

    @Composable
    private fun CreateFloatingActionButton(annotation: String) {
        FloatingActionButton(
            onClick = {
                fetchSaveAnnotation(annotation)
            }, elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = dimensionResource(id = R.dimen.size_8)
            ), shape = CircleShape, containerColor = Yellow
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_save),
                contentDescription = stringResource(R.string.text_accessibility_floating_button)
            )
        }
    }

    @Composable
    private fun CreateTxtFieldInsertAnnotation(
        value: String,
        onValueChange: (String) -> Unit,
        focusRequester: FocusRequester,
        showLabel: Boolean
    ) {
        TextField(value = value,
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = Yellow, focusedLabelColor = White, unfocusedLabelColor = Black
            ),
            textStyle = TextStyle(
                color = Black
            ),
            modifier = Modifier
                .testTag("txtInsertAnnotation")
                .fillMaxHeight()
                .fillMaxWidth()
                .focusRequester(focusRequester),
            label = {
                if (showLabel) {
                    Text(text = stringResource(R.string.txt_insert_annotation))
                }
            })
    }

    private fun fetchSaveAnnotation(annotation: String) = viewModel.saveAnnotation(annotation)

    private fun handleGetAnnotationSaved(
        viewState: State<String>, updateAnnotation: (String) -> Unit
    ) {
        when (viewState) {
            is State.Success -> {
                updateAnnotation(viewState.data)
            }

            is State.Loading -> {
                updateAnnotation("")
            }
        }
    }

    private fun handleSaveAnnotation(viewState: State<Unit>, context: Context) {
        if (viewState is State.Success) {
            Toast.makeText(
                context,
                context.getString(R.string.text_annotation_save_success),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

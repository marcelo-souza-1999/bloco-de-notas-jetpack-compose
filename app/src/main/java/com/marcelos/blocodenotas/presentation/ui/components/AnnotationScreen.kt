package com.marcelos.blocodenotas.presentation.ui.components

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.marcelos.blocodenotas.R
import com.marcelos.blocodenotas.presentation.theme.Black
import com.marcelos.blocodenotas.presentation.theme.TypographyTopBar
import com.marcelos.blocodenotas.presentation.theme.White
import com.marcelos.blocodenotas.presentation.theme.Yellow

@Composable
fun AnnotationScreen() {
    var annotation by remember { mutableStateOf("") }

    Scaffold(topBar = { CreateTopBar() },
        floatingActionButton = { CreateFloatingActionButton() }) { innerPadding ->
        Column(
            modifier = Modifier
                .background(White)
                .padding(innerPadding)
        ) {
            CreateTxtFieldInsertAnnotation(value = annotation,
                onValueChange = { annotation = it })
        }
    }
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
private fun CreateFloatingActionButton() {
    FloatingActionButton(
        onClick = {
            // Ação do FAB
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
private fun CreateTxtFieldInsertAnnotation(value: String, onValueChange: (String) -> Unit) {
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
            .fillMaxWidth(),
        label = {
            Text(text = stringResource(R.string.txt_insert_annotation))
        })
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun PreviewNotesScreen() {
    AnnotationScreen()
}
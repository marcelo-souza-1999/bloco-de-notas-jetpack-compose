package com.marcelos.blocodenotas.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.marcelos.blocodenotas.presentation.theme.Black
import com.marcelos.blocodenotas.presentation.theme.BlocoDeNotasTheme
import com.marcelos.blocodenotas.presentation.ui.components.AnnotationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlocoDeNotasTheme {
                setupStatusBarNative()
                AnnotationScreen()
            }
        }
    }

    private fun setupStatusBarNative() = enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.dark(
            scrim = Black.toArgb()
        )
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun PreviewMainActivity() {
    BlocoDeNotasTheme {
        AnnotationScreen()
    }
}

package com.marcelos.blocodenotas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.marcelos.blocodenotas.ui.theme.BlocoDeNotasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlocoDeNotasTheme {
                Log.d("MainActivity", "onCreate: MainActivity")
            }
        }
    }
}

package com.example.testofdatastore

import MyController
import MyView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.testofdatastore.ui.theme.TestOfDataStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestOfDataStoreTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Surface(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        MyView(controller = MyController(LocalContext.current))
    }
}

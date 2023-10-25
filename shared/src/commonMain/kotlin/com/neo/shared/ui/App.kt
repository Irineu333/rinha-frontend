package com.neo.shared.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.neo.shared.ui.home.HomeScreen

@Composable
fun App() {
    MaterialTheme {
        HomeScreen(Modifier.fillMaxSize())
    }
}
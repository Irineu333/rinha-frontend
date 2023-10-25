package com.neo.shared.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.neo.shared.ui.home.HomeScreen

@Composable
fun App(
    openFilePicker: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    content: String? = null
) {
    HomeScreen(
        modifier,
        isLoading,
        openFilePicker
    )
}
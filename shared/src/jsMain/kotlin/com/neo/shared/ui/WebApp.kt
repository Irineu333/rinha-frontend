package com.neo.shared.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WebApp() {

    App(
        openFilePicker = {
            // TODO: Implement file picker
        },
        modifier = Modifier.fillMaxSize(),
        content = null
    )
}
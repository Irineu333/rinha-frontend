package com.neo.shared.ui.viewer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

data class ViewerScreen(
    val fileName : String,
    val content: String
) : Screen {

    @Composable
    override fun Content() = Box(Modifier.fillMaxSize()) {
        Text(content)
    }
}
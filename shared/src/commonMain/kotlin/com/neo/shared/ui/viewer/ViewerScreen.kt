package com.neo.shared.ui.viewer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.neo.shared.core.component.File

data class ViewerScreen(
    val file : File
) : Screen {

    @Composable
    override fun Content() = Box(Modifier.fillMaxSize(), Alignment.Center) {
        Text(file.name)
    }
}
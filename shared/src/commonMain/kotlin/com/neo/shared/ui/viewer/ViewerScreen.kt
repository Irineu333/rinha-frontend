package com.neo.shared.ui.viewer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.neo.shared.core.component.File
import com.neo.shared.core.component.LinesViewer
import com.neo.shared.core.model.Resource

data class ViewerScreen(
    private val file: File
) : Screen {

    @Composable
    override fun Content() = Box(
        Modifier.fillMaxSize(),
        Alignment.Center
    ) {

        val viewModel = rememberScreenModel { ViewerViewModel(file) }

        when (val lines = viewModel.lines.collectAsState().value) {
            is Resource.Result.Failure -> {
                Text(lines.error, color = Color.Red)
            }

            is Resource.Result.Success -> {
                LinesViewer(lines.data)
            }

            Resource.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}
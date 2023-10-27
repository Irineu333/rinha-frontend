package com.neo.shared.ui.viewer

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.neo.shared.core.component.File
import com.neo.shared.core.component.LinesViewer
import com.neo.shared.core.model.Resource
import kotlinx.coroutines.launch

data class ViewerScreen(
    private val file: File
) : Screen {

    @Composable
    override fun Content() = Box(
        Modifier.fillMaxSize(),
        Alignment.Center
    ) {

        val viewModel = rememberScreenModel { ViewerViewModel(file) }

        val coroutineScope = rememberCoroutineScope()

        when (val lines = viewModel.lines.collectAsState().value) {
            is Resource.Result.Failure -> {
                Text(lines.error, color = Color.Red)
            }

            is Resource.Result.Success -> {
                val state = rememberLazyListState()

                LinesViewer(
                    lines = lines.data,
                    state = state,
                    contentPadding = PaddingValues(16.dp),
                    onToggle = {
                        coroutineScope.launch {
                            viewModel.toggle(it)
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )

                VerticalScrollbar(
                    adapter = rememberScrollbarAdapter(state),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .fillMaxHeight()
                )
            }

            Resource.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}
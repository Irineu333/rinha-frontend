package com.neo.shared.core.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.neo.shared.core.model.Line

@Composable
fun LinesViewer(
    lines: List<Line>,
    modifier: Modifier = Modifier
) = LazyColumn(modifier) {
    items(lines) { line ->
        LineViewer(line)
    }
}
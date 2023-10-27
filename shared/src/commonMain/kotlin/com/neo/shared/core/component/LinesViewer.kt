package com.neo.shared.core.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neo.shared.core.model.Element
import com.neo.shared.core.model.Line

@Composable
fun LinesViewer(
    lines: List<Line>,
    modifier: Modifier = Modifier,
    onToggle: (Element) -> Unit = {},
    contentPadding: PaddingValues = PaddingValues(0.dp)
) = LazyColumn(modifier, contentPadding = contentPadding) {
    items(lines) { line ->
        LineViewer(
            line = line,
            onToggle = onToggle
        )
    }
}
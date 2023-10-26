package com.neo.shared.core.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neo.shared.core.model.Line
import com.neo.shared.core.util.createArray

@Preview
@Composable
fun LinesViewerPreview() {

    val lines = createArray("file") {
        addObject {
            addLiteral("name", "neu")
            addLiteral("dev", true)
        }

        addObject(collapsed = true) {
            addLiteral("name", "vini")
            addLiteral("dev", true)
        }
    }

    MaterialTheme {
        LinesViewer(lines, Modifier.padding(16.dp))
    }
}
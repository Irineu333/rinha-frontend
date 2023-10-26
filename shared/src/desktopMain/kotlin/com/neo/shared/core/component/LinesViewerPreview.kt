package com.neo.shared.core.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neo.shared.core.model.Line
import com.neo.shared.core.util.createArray

@Preview
@Composable
fun LinesViewerPreview() {

    val lines = createArray("users") {
        addObject {
            addLiteral("name", "neu")
            addLiteral("dev", true)
            addArray("skills", collapsed = true) {}
        }

        addObject {
            addLiteral("name", "vini")
            addLiteral("dev", true)
            addArray("skills", collapsed = true) {}
        }
    }

    MaterialTheme {
       Box(Modifier.fillMaxSize(), Alignment.Center) {
           LinesViewer(lines, Modifier.padding(16.dp))
       }
    }
}
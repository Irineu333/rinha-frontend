package com.neo.shared.core.component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neo.shared.core.model.Line

@Preview
@Composable
fun LinesViewerPreview() {

    val lines = listOf(
        Line.Struct.Start(0, Line.Parent.Object("file"), Line.Type.ARRAY),
        Line.Struct.Start(1, Line.Parent.Array(0), Line.Type.OBJECT),
        Line.Literal(2, Line.Parent.Object("name"), "irineu"),
        Line.Literal(2, Line.Parent.Object("dev"), true),
        Line.Struct.End(1, Line.Parent.Array(0), Line.Type.OBJECT),
        Line.Struct.Start(1, Line.Parent.Array(1), Line.Type.OBJECT, collapsed = true),
        Line.Struct.End(0, Line.Parent.Object("file"), Line.Type.ARRAY),
    )

    MaterialTheme {
        LinesViewer(lines, Modifier.padding(16.dp))
    }
}
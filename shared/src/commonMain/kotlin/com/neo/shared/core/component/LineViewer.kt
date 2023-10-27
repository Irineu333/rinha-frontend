package com.neo.shared.core.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.neo.shared.core.model.Element
import com.neo.shared.core.model.Line
import com.neo.shared.core.model.ending
import com.neo.shared.core.model.opening

@Composable
fun LineViewer(
    line: Line,
    modifier: Modifier = Modifier,
    onToggle: (Element) -> Unit = {}
) = Row(
    modifier.drawBehind {
        for (indent in 1..line.indent) {

            val x = 16.dp.toPx() * indent - 16.dp.toPx() + 2.dp.toPx()

            drawLine(
                color = Color.Black,
                start = Offset(x = x, y = 0f),
                end = Offset(x = x, y = size.height),
            )
        }
    }
) {

    Spacer(Modifier.width(16.dp * line.indent))

    Row(
        Modifier.clickable(line is Line.Struct) {
            onToggle(checkNotNull((line as Line.Struct).ref))
        }
    ) {
        if (line !is Line.Struct.End) {

            when (val parent = line.parent) {
                is Line.Parent.Object -> {
                    Text(text = "\"${parent.key}\"")
                }

                is Line.Parent.Array -> {
                    Text(text = "${parent.index}")
                }
            }

            Text(text = ":", Modifier.padding(horizontal = 4.dp))
        }

        when (line) {
            is Line.Struct.Start -> {

                if (line.collapsed) {
                    Text(text = "${line.opening} ... ${line.ending}")
                } else {
                    Text(text = line.opening)
                }

            }

            is Line.Struct.End -> {
                Text(text = line.ending)
            }

            is Line.Literal -> {
                Text(text = "${line.value}")
            }
        }
    }
}

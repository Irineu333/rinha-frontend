import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neo.shared.core.component.LinesViewer
import com.neo.shared.core.model.Line

@Composable
fun App() {
    Box(Modifier.fillMaxSize(), Alignment.Center) {
        val lines = listOf(
            Line.Struct.Start(0, Line.Parent.Object("file"), Line.Type.ARRAY),
            Line.Struct.Start(1, Line.Parent.Array(0), Line.Type.OBJECT),
            Line.Literal(2, Line.Parent.Object("name"), "irineu"),
            Line.Literal(2, Line.Parent.Object("dev"), true),
            Line.Struct.End(1, Line.Parent.Array(0), Line.Type.OBJECT),
            Line.Struct.Start(1, Line.Parent.Array(1), Line.Type.OBJECT, collapsed = true),
            Line.Struct.End(0, Line.Parent.Object("file"), Line.Type.ARRAY),
        )

        LinesViewer(lines, Modifier.padding(16.dp))
    }
}
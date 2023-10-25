import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.neo.shared.ui.App
import com.neo.shared.ui.DesktopApp

fun main() = application {
    Window(
        title = "JSON Tree Viewer",
        onCloseRequest = ::exitApplication
    ) {
        MaterialTheme {
            DesktopApp()
        }
    }
}

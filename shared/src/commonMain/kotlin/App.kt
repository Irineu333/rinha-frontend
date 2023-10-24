import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun App() {
    Box(Modifier.fillMaxSize(), Alignment.Center) {
        Text("Olá, mundo!")
    }
}

@Preview
@Composable
fun AppPreview() {
    MaterialTheme {
        App()
    }
}
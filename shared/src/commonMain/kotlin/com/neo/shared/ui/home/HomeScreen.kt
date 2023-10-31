package com.neo.shared.ui.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.neo.shared.core.component.rememberFileManager
import com.neo.shared.ui.viewer.ViewerScreen
import kotlinx.coroutines.launch

object HomeScreen : Screen {

    @Composable
    override fun Content() = Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {

        val navigator = LocalNavigator.currentOrThrow

        val fileManager = rememberFileManager { file ->
            navigator.push(ViewerScreen(file))
        }

        Text(
            text = "JSON Tree Viewer",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        Spacer(Modifier.height(16.dp))

        val scope = rememberCoroutineScope()

        Button(
            enabled = !fileManager.isLoading,
            onClick = {
                scope.launch {
                    fileManager.openFilePicker()
                }
            },
        ) {
            AnimatedContent(fileManager.isLoading) {
                if (it) {
                    CircularProgressIndicator(
                        Modifier.size(18.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("select file")
                }
            }
        }
    }
}
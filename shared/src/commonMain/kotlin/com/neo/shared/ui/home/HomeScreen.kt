package com.neo.shared.ui.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

data class HomeScreen(
    val isLoading: State<Boolean>,
    val openFilePicker: () -> Unit
) : Screen {

    @Composable
    override fun Content() = Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {

        Text(
            text = "JSON Tree Viewer",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        Spacer(Modifier.height(16.dp))

        Button(
            enabled = !isLoading.value,
            onClick = { openFilePicker() },
        ) {
            AnimatedContent(isLoading.value) {
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


package com.neo.shared.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@Composable
fun DesktopApp() {

    var showFilePicker by remember { mutableStateOf(false) }

    var file by remember { mutableStateOf<File?>(null) }

    var content by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(file) {

        content = null

        content = withContext(Dispatchers.Default) {
            file?.readText()
        }
    }

    App(
        openFilePicker = {
            showFilePicker = true
        },
        modifier = Modifier.fillMaxSize(),
        isLoading = file != null && content == null,
        content = content
    )

    FilePicker(
        show = showFilePicker,
        fileExtensions = listOf("txt", "json")
    ) { selectedFile ->

        showFilePicker = false

        if (selectedFile != null) {
            file = selectedFile.platformFile as File
        }
    }
}
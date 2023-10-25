package com.neo.shared.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

@Composable
fun DesktopApp() {

    var showFilePicker by remember { mutableStateOf(false) }

    var file by remember { mutableStateOf<File?>(null) }

    var content by remember { mutableStateOf<String?>(null) }

    val coroutine = rememberCoroutineScope()

    App(
        openFilePicker = {
            showFilePicker = true
        },
        isLoading = file != null && content == null,
        content = content,
        modifier = Modifier.fillMaxSize()
    )

    FilePicker(
        show = showFilePicker,
        fileExtensions = listOf("txt", "json")
    ) { selectedFile ->

        showFilePicker = false

        if (selectedFile != null) {

            file = selectedFile.platformFile as File
            content = null

            coroutine.launch {
                content = withContext(Dispatchers.IO) {
                    file?.readText()
                }
            }
        }
    }
}
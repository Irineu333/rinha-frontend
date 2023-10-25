package com.neo.shared.ui

import androidx.compose.runtime.*
import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement
import org.w3c.files.File
import org.w3c.files.FileReader
import org.w3c.files.get

@Composable
fun WebApp() {

    val fileReader = remember {
        FileReader()
    }

    val fileInput = remember {
        document.getElementById("fileInput") as HTMLInputElement
    }

    var file by remember { mutableStateOf<File?>(null) }
    var content by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        fileInput.onchange = {

            file = fileInput.files?.get(0)

            content = null

            fileReader.readAsText(file!!)

            null
        }

        fileReader.onload = {
            content = fileReader.result as String

            null
        }
    }

    App(
        openFilePicker = { fileInput.click() },
        isLoading = file != null && content == null,
        content = content,
        fileName = file?.name
    )
}
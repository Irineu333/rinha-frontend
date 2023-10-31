package com.neo.shared.core.component

import androidx.compose.runtime.*

@Composable
fun rememberFileManager(
    onLoadListener: FileManager.OnLoadListener
) = remember {
    FileManagerFactory.create()
}.apply {
    this.onLoadListener = onLoadListener
}

expect object FileManagerFactory {
    fun create(): FileManager
}

abstract class FileManager {

    protected var state by mutableStateOf(FileState())

    val isLoading get() = state.isLoading

    var onLoadListener: OnLoadListener? = null

    abstract suspend fun openFilePicker()

    fun interface OnLoadListener {
        operator fun invoke(file: File)
    }
}

data class File(
    val name: String,
    val content: String,
)

data class FileState(
    val isLoading: Boolean = false,
    val file: File? = null,
)
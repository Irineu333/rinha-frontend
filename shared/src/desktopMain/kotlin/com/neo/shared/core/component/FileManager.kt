package com.neo.shared.core.component

import com.neo.shared.core.model.JsonFileFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.swing.JFileChooser

actual object FileManagerFactory {
    actual fun create(): FileManager = FileManagerImpl()
}

class FileManagerImpl : FileManager() {

    override suspend fun openFilePicker() {
        JFileChooser().apply {
            fileSelectionMode = JFileChooser.FILES_ONLY

            fileFilter = JsonFileFilter

            showOpenDialog(null)

            if (selectedFile != null) {
                state = state.copy(isLoading = true)

                val file = File(
                    name = selectedFile.name,
                    content = withContext(Dispatchers.IO) {
                        selectedFile.readText()
                    }
                )

                state = state.copy(
                    isLoading = false,
                    file = file
                )

                onLoadListener?.invoke(file)
            }
        }
    }
}
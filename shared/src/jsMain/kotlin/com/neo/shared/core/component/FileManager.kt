package com.neo.shared.core.component

import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement
import org.w3c.files.FileReader
import org.w3c.files.get

actual object FileManagerFactory {
    actual fun create(): FileManager = FileManagerImpl()
}

class FileManagerImpl : FileManager() {

    private val reader = FileReader()
    private val input = document.getElementById("fileInput") as HTMLInputElement
    private val file get() = input.files?.get(0)

    init {

        reader.onload = {

            val file = File(
                name = checkNotNull(file?.name),
                content = reader.result.toString()
            )

            state = state.copy(
                isLoading = false,
                file = file
            )

            onLoadListener?.invoke(file)

            null
        }

        input.onchange = {

            file?.let { file ->
                state = state.copy(isLoading = true)

                reader.readAsText(file)
            }

            null
        }
    }

    override suspend fun openFilePicker() {
        input.click()
    }
}
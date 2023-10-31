package com.neo.shared.core.model

import java.io.File
import javax.swing.filechooser.FileFilter

object JsonFileFilter : FileFilter() {
    override fun accept(file: File): Boolean {
        return file.name.endsWith(".txt") ||
                file.name.endsWith(".json") ||
                file.isDirectory
    }

    override fun getDescription(): String {
        return "JSON files"
    }
}
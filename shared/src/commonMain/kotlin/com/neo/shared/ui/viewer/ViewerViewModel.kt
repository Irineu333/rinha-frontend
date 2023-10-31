package com.neo.shared.ui.viewer

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.neo.shared.core.component.File
import com.neo.shared.core.model.Element
import com.neo.shared.core.model.Line
import com.neo.shared.core.model.Resource
import com.neo.shared.core.model.getLines
import com.neo.shared.core.util.toElement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ViewerViewModel(private val file: File) : ScreenModel {

    private val json = Json

    private val _elements = MutableStateFlow<Resource<Element, String>>(Resource.Loading)

    private val _lines = MutableStateFlow<Resource<List<Line>, String>>(Resource.Loading)
    val lines = _lines.asStateFlow()

    init {
        coroutineScope.launch {
            tokenizeJson()
            renderJson()
        }
    }

    private suspend fun tokenizeJson() {
        _elements.value = Resource.Loading

        _elements.value = runCatching {
            Resource.Result.Success(
                withContext(Dispatchers.Default) {
                    json.parseToJsonElement(file.content).toElement()
                }
            )
        }.getOrElse {
            Resource.Result.Failure("Invalid JSON")
        }
    }

    private suspend fun renderJson() {
        _lines.value = when (val value = _elements.value) {
            is Resource.Loading -> {
                Resource.Loading
            }

            is Resource.Result.Failure -> {
                Resource.Result.Failure(value.error)
            }

            is Resource.Result.Success -> {
                withContext(Dispatchers.Default) {
                    Resource.Result.Success(
                        value.data.getLines(file.name)
                    )
                }
            }
        }
    }

    fun toggleExpansion(element: Element.Struct) = coroutineScope.launch {
        element.isCollapsed = !element.isCollapsed
        renderJson()
    }
}
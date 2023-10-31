package com.neo.shared.ui.viewer

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.neo.shared.core.component.File
import com.neo.shared.core.model.Element
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

    val lines = _elements.map {
        when (it) {
            is Resource.Result.Success -> {
                Resource.Result.Success(
                    it.data.getLines(file.name)
                )
            }

            is Resource.Result.Failure -> {
                Resource.Result.Failure(it.error)
            }

            Resource.Loading -> Resource.Loading
        }
    }.stateIn(
        scope = coroutineScope,
        initialValue = Resource.Loading,
        started = SharingStarted.WhileSubscribed()
    )

    init {
        tokenizeJson(file.content)
    }

    private fun tokenizeJson(content: String) = coroutineScope.launch {

        _elements.value = Resource.Loading

        _elements.value = runCatching {
            Resource.Result.Success(
                withContext(Dispatchers.Default) {
                    json.parseToJsonElement(content).toElement()
                }
            )
        }.getOrElse {
            Resource.Result.Failure("Invalid JSON")
        }
    }

    suspend fun toggle(element: Element) {
        _elements.update {
            if (it is Resource.Result.Success) {
                Resource.Result.Success(
                    toggle(it.data, element)
                )
            } else {
                it
            }
        }
    }

    private suspend fun toggle(
        element: Element,
        target: Element
    ): Element {
        return withContext(Dispatchers.Default) {
            when (element) {
                is Element.Struct.Array -> {
                    if (element == target) {
                        element.copy(isCollapsed = !element.isCollapsed)
                    } else {
                        element.copy(
                            elements = element.elements.map {
                                toggle(it, target)
                            }
                        )
                    }
                }

                is Element.Struct.Object -> {
                    if (element == target) {
                        element.copy(isCollapsed = !element.isCollapsed)
                    } else {
                        element.copy(
                            properties = element.properties.map {
                                it.key to toggle(it.value, target)
                            }.toMap()
                        )
                    }
                }

                else -> element
            }
        }
    }
}
package com.neo.shared.ui.viewer

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.neo.shared.core.component.File
import com.neo.shared.core.model.Element
import com.neo.shared.core.model.Resource
import com.neo.shared.core.model.getLines
import com.neo.shared.core.util.toElement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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
        handle(file.content)
    }

    fun handle(content: String) = coroutineScope.launch {

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
}
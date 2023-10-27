package com.neo.shared.core.util

import com.neo.shared.core.model.Element
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

fun JsonElement.toElement(): Element {

    return when (this) {
        is JsonPrimitive -> {
            Element.Literal(value = this)
        }

        is JsonObject -> {
            Element.Object(
                properties = map {
                    it.key to it.value.toElement()
                }.toMap()
            )
        }

        is JsonArray -> {
            Element.Array(
                elements = map {
                    it.toElement()
                }
            )
        }
    }
}
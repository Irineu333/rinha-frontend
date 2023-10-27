package com.neo.shared.core.model

import com.neo.shared.core.util.createObject

sealed class Element {

    data class Literal(
        val value: Any
    ) : Element()

    data class Object(
        val properties: Map<String, Element>,
        val isCollapsed: Boolean = false
    ) : Element()


    data class Array(
        val elements: List<Element>,
        val isCollapsed: Boolean = false
    ) : Element()
}

fun Element.getLines(origin: String): List<Line> {
    return createObject(origin) {
        addLiteral("text", "Hello World!")
    }
}
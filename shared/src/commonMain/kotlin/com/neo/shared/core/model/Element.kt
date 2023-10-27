package com.neo.shared.core.model

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
    return LinesFactory(
        fileName = origin,
        origin = this
    ).build(
        element = this,
        indent = 0,
        parent = Line.Parent.Object(origin)
    )
}
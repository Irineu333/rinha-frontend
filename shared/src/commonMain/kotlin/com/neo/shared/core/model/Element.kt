package com.neo.shared.core.model

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4

sealed class Element {

    abstract val uuid : Uuid

    data class Literal(
        override val uuid: Uuid = uuid4(),
        val value: Any
    ) : Element()

    data class Object(
        override val uuid: Uuid = uuid4(),
        val properties: Map<String, Element>,
        val isCollapsed: Boolean = false,
    ) : Element()


    data class Array(
        override val uuid: Uuid = uuid4(),
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
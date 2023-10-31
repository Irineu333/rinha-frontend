package com.neo.shared.core.model

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4

sealed class Element {

    abstract val uuid: Uuid

    data class Literal(
        override val uuid: Uuid = uuid4(),
        val value: Any
    ) : Element()

    sealed class Struct : Element() {

        abstract var isCollapsed: Boolean

        data class Object(
            override val uuid: Uuid = uuid4(),
            val properties: Map<String, Element>,
            override var isCollapsed: Boolean = false,
        ) : Struct()


        data class Array(
            override val uuid: Uuid = uuid4(),
            val elements: List<Element>,
            override var isCollapsed: Boolean = false
        ) : Struct()

    }
}

fun Element.getLines(origin: String): List<Line> {
    return LinesFactory(
        fileName = origin,
        origin = this
    ).build()
}
package com.neo.shared.core.util

import com.neo.shared.core.model.Line

class ObjectScope(private val indent: Int) {
    val lines = mutableListOf<Line>()

    fun addLiteral(key: String, value: Any) {
        lines.add(
            Line.Literal(
                indent,
                Line.Parent.Object(key),
                value
            )
        )
    }

    fun addObject(key: String, collapsed: Boolean = false, build: ObjectScope.() -> Unit) {
        lines.add(
            Line.Struct.Start(
                indent,
                Line.Parent.Object(key),
                Line.Type.OBJECT,
                collapsed = collapsed
            )
        )

        if (collapsed) return

        lines.addAll(ObjectScope(indent + 1).apply(build).lines)

        lines.add(
            Line.Struct.End(
                indent,
                Line.Parent.Object(key),
                Line.Type.OBJECT
            )
        )
    }

    fun addArray(key: String, collapsed: Boolean = false, build: ArrayScope.() -> Unit) {
        lines.add(
            Line.Struct.Start(
                indent,
                Line.Parent.Object(key),
                Line.Type.ARRAY,
                collapsed = collapsed
            )
        )

        if (collapsed) return

        lines.addAll(ArrayScope(indent + 1).apply(build).lines)

        lines.add(
            Line.Struct.End(
                indent,
                Line.Parent.Object(key),
                Line.Type.ARRAY
            )
        )
    }
}

class ArrayScope(private val indent: Int) {

    val lines = mutableListOf<Line>()

    private var mIndex = 0

    fun addLiteral(index: Int = mIndex++, value: Any) {
        lines.add(
            Line.Literal(
                indent,
                Line.Parent.Array(index),
                value
            )
        )
    }

    fun addObject(index: Int = mIndex++, collapsed: Boolean = false, build: ObjectScope.() -> Unit) {
        lines.add(
            Line.Struct.Start(
                indent,
                Line.Parent.Array(index),
                Line.Type.OBJECT,
                collapsed = collapsed
            )
        )

        if (collapsed) return

        lines.addAll(ObjectScope(indent + 1).apply(build).lines)

        lines.add(
            Line.Struct.End(
                indent,
                Line.Parent.Array(index),
                Line.Type.OBJECT
            )
        )
    }

    fun addArray(index: Int = mIndex++, collapsed: Boolean = false, build: ArrayScope.() -> Unit) {
        lines.add(
            Line.Struct.Start(
                indent,
                Line.Parent.Array(index),
                Line.Type.ARRAY,
                collapsed = collapsed
            )
        )

        if (collapsed) return

        lines.addAll(ArrayScope(indent + 1).apply(build).lines)

        lines.add(
            Line.Struct.End(
                indent,
                Line.Parent.Array(index),
                Line.Type.ARRAY
            )
        )
    }
}

fun createObject(
    key: String,
    build: ObjectScope.() -> Unit
) = buildList {

    add(
        Line.Struct.Start(
            0,
            Line.Parent.Object(key),
            Line.Type.OBJECT
        )
    )

    addAll(ObjectScope(1).apply(build).lines)

    add(
        Line.Struct.End(
            0,
            Line.Parent.Object(key),
            Line.Type.OBJECT
        )
    )
}

fun createArray(
    key: String,
    build: ArrayScope.() -> Unit
) = buildList {

    add(
        Line.Struct.Start(
            0,
            Line.Parent.Object(key),
            Line.Type.ARRAY
        )
    )

    addAll(ArrayScope(1).apply(build).lines)

    add(
        Line.Struct.End(
            0,
            Line.Parent.Object(key),
            Line.Type.ARRAY
        )
    )
}
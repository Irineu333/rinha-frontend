package com.neo.shared.core.util

import com.neo.shared.core.model.Line

abstract class JsonScope(private val indent: Int) {
    private val lines = mutableListOf<Line>()

    fun addLiteral(line: Line.Literal) {
        lines.add(line)
    }

    fun addStruct(
        scope: JsonScope,
        parent: Line.Parent,
        type: Line.Type,
        collapsed: Boolean = false
    ) {

        lines.add(
            Line.Struct.Start(
                indent,
                parent,
                type,
                collapsed
            )
        )

        if (collapsed) return

        lines.addAll(scope.lines)

        lines.add(
            Line.Struct.End(
                indent,
                parent,
                type
            )
        )
    }

    fun build() = lines.toList()
}

class ObjectScope(private val indent: Int) : JsonScope(indent) {

    fun addLiteral(key: String, value: Any) {
        addLiteral(
            Line.Literal(
                indent,
                Line.Parent.Object(key),
                value
            )
        )
    }

    fun addObject(
        key: String,
        collapsed: Boolean = false,
        build: ObjectScope.() -> Unit
    ) {
        addStruct(
            ObjectScope(indent + 1).apply(build),
            Line.Parent.Object(key),
            Line.Type.OBJECT,
            collapsed
        )
    }

    fun addArray(
        key: String,
        collapsed: Boolean = false,
        build: ArrayScope.() -> Unit
    ) {
        addStruct(
            ArrayScope(indent + 1).apply(build),
            Line.Parent.Object(key),
            Line.Type.ARRAY,
            collapsed
        )
    }
}

class ArrayScope(private val indent: Int) : JsonScope(indent) {

    private var mIndex = 0

    fun addLiteral(index: Int = mIndex++, value: Any) {
        addLiteral(
            Line.Literal(
                indent,
                Line.Parent.Array(index),
                value
            )
        )
    }

    fun addObject(
        index: Int = mIndex++,
        collapsed: Boolean = false,
        build: ObjectScope.() -> Unit
    ) {
        addStruct(
            ObjectScope(indent + 1).apply(build),
            Line.Parent.Array(index),
            Line.Type.OBJECT,
            collapsed
        )
    }

    fun addArray(
        index: Int = mIndex++,
        collapsed: Boolean = false,
        build: ArrayScope.() -> Unit
    ) {
        addStruct(
            ArrayScope(indent + 1).apply(build),
            Line.Parent.Array(index),
            Line.Type.ARRAY,
            collapsed
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

    addAll(ObjectScope(1).apply(build).build())

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
    scope: ArrayScope.() -> Unit
) = buildList {

    add(
        Line.Struct.Start(
            0,
            Line.Parent.Object(key),
            Line.Type.ARRAY
        )
    )

    addAll(ArrayScope(1).apply(scope).build())

    add(
        Line.Struct.End(
            0,
            Line.Parent.Object(key),
            Line.Type.ARRAY
        )
    )
}
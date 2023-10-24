package com.neo.shared.core.model

sealed interface Line {

    val indent: Int
    val parent: Parent


    sealed interface Struct : Line {

        val type: Type

        data class Start(
            override val indent: Int,
            override val parent: Parent,
            override val type: Type,
            val collapsed: Boolean = false
        ) : Struct

        data class End(
            override val indent: Int,
            override val parent: Parent,
            override val type: Type,
        ) : Struct
    }

    data class Literal(
        override val indent: Int,
        override val parent: Parent,
        val value: Any
    ) : Line

    enum class Type {
        OBJECT,
        ARRAY
    }

    sealed class Parent {

        data class Object(
            val key: String
        ) : Parent()

        data class Array(
            val index: Int
        ) : Parent()
    }
}

val Line.Struct.opening
    get() = when (type) {
        Line.Type.ARRAY -> "["
        Line.Type.OBJECT -> "{"
    }

val Line.Struct.ending
    get() = when (type) {
        Line.Type.ARRAY -> "]"
        Line.Type.OBJECT -> "}"
    }
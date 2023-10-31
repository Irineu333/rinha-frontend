package com.neo.shared.core.model

data class LinesFactory(
    private val fileName: String,
    private val origin: Element
) {
    fun build(
        element: Element = origin,
        indent: Int = 0,
        parent: Line.Parent = Line.Parent.Object(fileName)
    ): List<Line> {
        return buildList {
            when (element) {
                is Element.Struct.Array -> {
                    add(
                        Line.Struct.Start(
                            indent = indent,
                            parent = parent,
                            type = Line.Type.ARRAY,
                            collapsed = element.isCollapsed,
                            ref = element
                        )
                    )

                    if (element.isCollapsed) return@buildList

                    element.elements.forEachIndexed { index, it ->
                        addAll(
                            build(
                                element = it,
                                indent = indent + 1,
                                parent = Line.Parent.Array(index),
                            )
                        )
                    }

                    add(
                        Line.Struct.End(
                            indent = indent,
                            parent = parent,
                            type = Line.Type.ARRAY,
                            ref = element
                        )
                    )
                }

                is Element.Struct.Object -> {
                    add(
                        Line.Struct.Start(
                            indent = indent,
                            parent = parent,
                            type = Line.Type.OBJECT,
                            collapsed = element.isCollapsed,
                            ref = element
                        )
                    )

                    if (element.isCollapsed) return@buildList

                    element.properties.forEach {
                        addAll(
                            build(
                                element = it.value,
                                indent = indent + 1,
                                parent = Line.Parent.Object(it.key)
                            )
                        )
                    }

                    add(
                        Line.Struct.End(
                            indent = indent,
                            parent = parent,
                            type = Line.Type.OBJECT,
                            ref = element
                        )
                    )
                }

                is Element.Literal -> {
                    add(
                        Line.Literal(
                            indent = indent,
                            parent = parent,
                            value = element.value.toString(),
                        )
                    )
                }
            }
        }
    }
}
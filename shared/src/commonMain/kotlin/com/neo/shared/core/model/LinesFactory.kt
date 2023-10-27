package com.neo.shared.core.model

data class LinesFactory(
    private val fileName: String,
    private val origin: Element
) {
    fun build(
        element: Element,
        indent: Int = 0,
        parent: Line.Parent
    ): List<Line> {
        return buildList {
            when (element) {
                is Element.Array -> {
                    add(
                        Line.Struct.Start(
                            indent = indent,
                            parent = parent,
                            type = Line.Type.ARRAY,
                            collapsed = element.isCollapsed
                        )
                    )

                    if (element.isCollapsed) return@buildList

                    element.elements.forEachIndexed { index, it ->
                        addAll(
                            build(
                                element = it,
                                indent = indent + 1,
                                parent = Line.Parent.Array(index)
                            )
                        )
                    }

                    add(
                        Line.Struct.End(
                            indent = indent,
                            parent = parent,
                            type = Line.Type.ARRAY
                        )
                    )
                }

                is Element.Object -> {
                    add(
                        Line.Struct.Start(
                            indent = indent,
                            parent = parent,
                            type = Line.Type.OBJECT,
                            collapsed = element.isCollapsed
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
                            type = Line.Type.OBJECT
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
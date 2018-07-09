package canvas

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RectangleTest {

    @Test
    fun `a 10x10 rectangle is composed of 4 lines (1, 1)-(10, 1), (1, 1)-(1, 10), (1, 10)-(10, 10) & (10, 1)-(10, 10)`() {
        val expected = listOf(
                Line(Point(1, 1), Point(10, 1)),
                Line(Point(1, 1), Point(1, 10)),
                Line(Point(1, 10), Point(10, 10)),
                Line(Point(10, 10), Point(10, 1)))
        val square = Rectangle(Point(1, 1), Point(10, 10))
        val actual = square.lines
        expected.forEach { assertTrue(actual.contains(it))}
    }
}
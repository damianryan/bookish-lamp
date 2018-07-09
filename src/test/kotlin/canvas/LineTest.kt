package canvas

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class LineTest {

    // equality
    @Test
    fun `a line from (1, 1) to (1, 10) is the same as a line from (1, 10) to (1, 1)`() {
        val left = Line(Point(1, 1), Point(1, 10))
        val right = Line(Point (1, 10), Point (1, 1))
        assertEquals(left, right)
    }

    // orientation
    @Test
    fun `a line with the same X coordinates but different Y coordinates for start and end is vertical`() {
        assertEquals(Line.Orientation.VERTICAL, Line(Point(0, 0), Point(0, 9)).orientation)
    }

    @Test
    fun `a line with the same Y coordinates but different X coordinates for start and end is horizontal`() {
        assertEquals(Line.Orientation.HORIZONTAL, Line(Point(0, 0), Point(9, 0)).orientation)
    }

    // vertical lines: length, origin, end, normalisedOrigin, normalisedEnd
    // vertical line from (0, 0) to (0, 9)
    @Test
    fun `a vertical line from (0, 0) to (0, 9) has a length of 10`() {
        assertEquals(10, Line(Point(0,0 ), Point(0, 9)).length)
    }

    @Test
    fun `a vertical line from (0, 0) to (0, 9) has points (0, 0), (0, 1) up to (0, 9)`() {
        val expected = mutableListOf<Point>()
        for (y in 0..9) {
            expected.add(Point(0, y))
        }
        assertEquals(expected.toList(), Line(Point(0,0 ), Point(0, 9)).points)
    }

    @Test
    fun `a vertical line from (0, 0) to (0, 9) has an origin of (0, 0)`() {
        assertEquals(Point(0, 0), Line(Point(0,0 ), Point(0, 9)).origin)
    }

    @Test
    fun `a vertical line from (0, 0) to (0, 9) has an end of (0, 9)`() {
        assertEquals(Point(0, 9), Line(Point(0,0 ), Point(0, 9)).end)
    }

    // non-normalised vertical line from (0, 9) to (0, 0)
    @Test
    fun `a vertical line from (0, 9) to (0, 0) has a length of 10`() {
        assertEquals(10, Line(Point(0,9 ), Point(0, 0)).length)
    }

    @Test
    fun `a vertical line from (0, 9) to (0, 0) has points (0, 0), (0, 1) up to (0, 9)`() {
        val expected = mutableListOf<Point>()
        for (y in 0..9) {
            expected.add(Point(0, y))
        }
        assertEquals(expected.toList(), Line(Point(0,9 ), Point(0, 0)).points)
    }

    @Test
    fun `a vertical line from (0, 9) to (0, 0) has an origin of (0, 9)`() {
        assertEquals(Point(0, 9), Line(Point(0,9 ), Point(0, 0)).origin)
    }

    @Test
    fun `a vertical line from (0, 9) to (0, 0) has an end of (0, 0)`() {
        assertEquals(Point(0, 0), Line(Point(0,9 ), Point(0, 0)).end)
    }

    // horizontal lines: length, origin, end, normalisedOrigin, normalisedEnd
    // horizontal line from (0, 0) to (9, 0)
    @Test
    fun `a horizontal line from (0, 0) to (9, 0) has a length of 10`() {
        assertEquals(10, Line(Point(0,0 ), Point(9, 0)).length)
    }

    @Test
    fun `a horizontal line from (0, 0) to (9, 0) has points (0, 0), (1, 0) up to (9, 0)`() {
        val expected = mutableListOf<Point>()
        for (x in 0..9) {
            expected.add(Point(x, 0))
        }
        assertEquals(expected.toList(), Line(Point(0,0 ), Point(9, 0)).points)
    }

    @Test
    fun `a horizontal line from (0, 0) to (9, 0) has an origin of (0, 0)`() {
        assertEquals(Point(0, 0), Line(Point(0,0 ), Point(9, 0)).origin)
    }

    @Test
    fun `a horizontal line from (0, 0) to (9, 0) has an end of (9, 0)`() {
        assertEquals(Point(9, 0), Line(Point(0,0 ), Point(9, 0)).end)
    }

    // non-normalised horizontal line from (9, 0) to (0, 0)
    @Test
    fun `a horizontal line from (9, 0) to (0, 0) has a length of 10`() {
        assertEquals(10, Line(Point(9,0 ), Point(0, 0)).length)
    }

    @Test
    fun `a horizontal line from (9, 0) to (0, 0) has points (0, 0), (1, 0) up to (9, 0)`() {
        val expected = mutableListOf<Point>()
        for (x in 0..9) {
            expected.add(Point(x, 0))
        }
        assertEquals(expected.toList(), Line(Point(9,0 ), Point(0, 0)).points)
    }

    @Test
    fun `a horizontal line from (9, 0) to (0, 0) has an origin of (9, 0)`() {
        assertEquals(Point(9, 0), Line(Point(9,0 ), Point(0, 0)).origin)
    }

    @Test
    fun `a horizontal line from (9, 0) to (0, 0) has an end of (0, 0)`() {
        assertEquals(Point(0, 0), Line(Point(9,0 ), Point(0, 0)).end)
    }

    // diagonal lines: a no-no
    @Test
    fun `a diagonal line cannot be constructed`() {
        assertThrows<IllegalArgumentException> { Line(Point(0, 0), Point(9, 9)) }
    }
}
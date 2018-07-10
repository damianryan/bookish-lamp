package canvas

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CanvasTest {

    @Test
    fun `an overly long line does not fit onto a canvas`() {
        val canvas = Canvas(20, 5)
        val line = Line(Point(1, 3), Point(21, 3))
        assertThrows<IllegalArgumentException> {
            canvas.addPointyThing(line)
        }
    }

    @Test
    fun `adding a line to an empty canvas`() {
        val canvas = Canvas(50, 80)
        assertEquals(0, canvas.points.size)
        val newCanvas = canvas.addPointyThing(Line(Point(10, 10), Point(10, 39)))
        assertEquals(30, newCanvas.points.size)
    }

    @Test
    fun `an empty 20 by 5 canvas`() {
        val expected =
                """
                ----------------------
                |                    |
                |                    |
                |                    |
                |                    |
                |                    |
                ----------------------
                """.trimIndent()
        val canvas = Canvas(20, 5)
        assertEquals(expected, canvas.toString())
    }

    @Test
    fun `a 20 by 5 canvas with a line (1, 3) to (7, 3)`() {
        val expected =
                """
                ----------------------
                |                    |
                |                    |
                |xxxxxxx             |
                |                    |
                |                    |
                ----------------------
                """.trimIndent()
        var canvas = Canvas(20, 5)
        canvas = canvas.addPointyThing(Line(Point(1, 3), Point(7, 3)))
        assertEquals(expected, canvas.toString())
    }

    @Test
    fun `a 20 by 5 canvas with 2 lines (1, 3) to (7, 3) and (7, 1) to (7, 3)`() {
        val expected =
                """
                ----------------------
                |      x             |
                |      x             |
                |xxxxxxx             |
                |                    |
                |                    |
                ----------------------
                """.trimIndent()
        var canvas = Canvas(20, 5)
        canvas = canvas.addPointyThing(Line(Point(1, 3), Point(7, 3)))
        canvas = canvas.addPointyThing(Line(Point(7, 1), Point(7, 3)))
        assertEquals(expected, canvas.toString())
    }

    @Test
    fun `a 20 by 5 canvas with 2 lines (1, 3) to (7, 3) and (7, 1) to (7, 3) and a rectangle (15, 2) to (20, 5)`() {
        val expected =
                """
                ----------------------
                |      x             |
                |      x       xxxxxx|
                |xxxxxxx       x    x|
                |              x    x|
                |              xxxxxx|
                ----------------------
                """.trimIndent()
        var canvas = Canvas(20, 5)
        canvas = canvas.addPointyThing(Line(Point(1, 3), Point(7, 3)))
        canvas = canvas.addPointyThing(Line(Point(7, 1), Point(7, 3)))
        canvas = canvas.addPointyThing(Rectangle(Point(15, 2), Point(20, 5)))
        assertEquals(expected, canvas.toString())
    }
}
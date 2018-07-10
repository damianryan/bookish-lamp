package canvas

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ParserTest {

    @Test
    fun `unknown command with no existing canvas returns nothing`() {
        assertNull(Parser().parse("G"))
    }

    @Test
    fun `unknown command with existing canvas returns unaltered canvas`() {
        val canvas = Canvas(20, 5)
        assertEquals(canvas, Parser().parse("G", canvas))
    }

    @Test
    fun `C 20 5 with no existing canvas returns a new 20x5 canvas`() {
        val canvas = Canvas(20, 5)
        assertEquals(canvas, Parser().parse("C 20 5"))
    }

    @Test
    fun `C 20 5 with an existing 10x10 canvas returns a new 20x5 canvas`() {
        val canvas = Canvas(20, 5)
        assertEquals(canvas, Parser().parse("C 20 5", Canvas(10, 10)))
    }

    @Test
    fun `C 20 (missing height) with an existing 10x10 canvas returns existing 10x10 canvas`() {
        val canvas = Canvas(10, 10)
        assertEquals(canvas, Parser().parse("C 20", canvas))
    }

    @Test
    fun `C 20 (missing height) with no existing canvas returns nothing`() {
        assertNull(Parser().parse("C 20"))
    }

    @Test
    fun `C 20 A (non-integer height) with no existing canvas returns nothing`() {
        assertNull(Parser().parse("C 20 A"))
    }

    @Test
    fun `C 20 0 (non-positive integer height) with no existing canvas returns nothing`() {
        assertNull(Parser().parse("C 20 A"))
    }

    @Test
    fun `L 1 1 10 1 with no existing canvas returns nothing`() {
        assertNull(Parser().parse("L 1 1 10 1"))
    }

    @Test
    fun `L 1 1 10 1 with existing 20x5 canvas returns new canvas with line between (1, 1) and (10, 1)`() {
        val original = Canvas(20, 5)
        val withLine = original.addPointyThing(Line(Point(1, 1), Point(10, 1)))
        assertEquals(withLine, Parser().parse("L 1 1 10 1", original))
    }

    @Test
    fun `L 1 1 10 (missing y2) with existing 20x5 canvas returns original canvas`() {
        val original = Canvas(20, 5)
        assertEquals(original, Parser().parse("L 1 1 10", original))
    }

    @Test
    fun `L 1 1 A 1 (non-integer x2) with existing 20x5 canvas returns original canvas`() {
        val original = Canvas(20, 5)
        assertEquals(original, Parser().parse("L 1 1 A 1", original))
    }

    @Test
    fun `R 15 2 20 5 with no existing canvas returns nothing`() {
        assertNull(Parser().parse("R 15 2 20 5"))
    }

    @Test
    fun `R 15 2 20 5 (missing y2) with existing 20x5 canvas returns original canvas`() {
        val original = Canvas(20, 5)
        assertEquals(original, Parser().parse("R 15 2 20", original))
    }

    @Test
    fun `R 15 2 1A 5 (non-integer x2) with existing 20x5 canvas returns original canvas`() {
        val original = Canvas(20, 5)
        assertEquals(original, Parser().parse("R 15 2 1A 5", original))
    }
}
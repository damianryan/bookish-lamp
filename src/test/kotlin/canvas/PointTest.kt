package canvas

import junit.framework.Assert.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PointTest {

    @Test
    fun `a point cannot have a negative X coordinate`() {
        assertThrows<IllegalArgumentException> {
            Point(-1, 0)
        }
    }

    @Test
    fun `a point cannot have a negative Y coordinate`() {
        assertThrows<IllegalArgumentException> {
            Point(0, -1)
        }
    }

    @Test
    fun `points with non-negative coordinates are valid`() {
        Point(0, 0)
        Point(0, 1)
        Point(1, 0)
        Point(1, 1)
    }

    @Test
    fun `(0, 0) comes before (0, 1)`() {
        assertTrue(Point(0,0).compareTo(Point(0, 1)) < 0)
    }

    @Test
    fun `(0, 0) comes before (1, 0)`() {
        assertTrue(Point(0,0).compareTo(Point(1, 0)) < 0)
    }

    @Test
    fun `(1, 2) comes before (1, 3)`() {
        assertTrue(Point(1,2).compareTo(Point(1, 3)) < 0)
    }

    @Test
    fun `(2, 2) comes after (1, 3)`() {
        assertTrue(Point(2,2).compareTo(Point(1, 3)) > 0)
    }
}
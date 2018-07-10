package canvas

import java.lang.IllegalArgumentException

/**
 * Canvas represents a drawing area of a specifiable width and height, and the points within that area that are drawn
 * as a result of adding lines and/or rectangles.
 *
 * @property width The width of the drawable area.
 * @property height The height of the drawable area.
 * @property points The non-blank points within the drawable area.
 * @constructor Creates a new drawable area.
 *
 * @since 09-July-2018
 * @author Damian Ryan
 */
data class Canvas(val width: Int, val height: Int, val points: Set<Point> = emptySet()) {

    /**
     * Adds a pointy thing like a line or a rectangle to the drawable area. If the pointy thing does not fit
     * entirely withing the bounds of the drawable area, it is not added.
     *
     * @param[thing] A pointy thing.
     * @return A new Canvas containing the added pointy thing.
     * @throws IllegalArgumentException If the point thing does not fit entirely within the drawable area.
     */
    fun addPointyThing(thing: Pointy): Canvas {
        if (canFit(thing)) {
            return copy(points = points.plus(thing.points))
        } else {
            throw IllegalArgumentException("Pointy thing does not fit within bounds of this drawable area")
        }
    }

    /**
     * Tests if a point thing fits entirely within the bounds of the drawable area.
     *
     * @param[thing] A pointy thing.
     * @return true if
     */
    private fun canFit(thing: Pointy): Boolean {
        return null == thing.points.find { it.x > width || it.y > height }
    }

    /**
     * A string representation of this drawable area, surrounded by a border formed of hyphens (horizontal
     * borders) and pipes (vertical borders), with 'x' characters for non-blank points and spaces for blank areas.
     *
     * @return A string representation of the state of this drawing area.
     */
    override fun toString(): String {
        val s = StringBuilder()
        (1..width + 2).forEach { s.append("-") }; s.append('\n') // extra 2 hyphens to allow for the vertical borders
        for (y in 1..height) {
            s.append('|')
            for (x in 1..width) {
                s.append(if (points.contains(Point(x, y))) 'x' else ' ')
            }
            s.append("|\n")
        }
        (1..width + 2).forEach { s.append("-") }
        return s.toString()
    }
}
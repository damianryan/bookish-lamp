package canvas

/**
 * Point represents a two-dimensional point on a drawing area.
 *
 * @property x The X coordinate of this point.
 * @property y The Y coordinate of this point.
 * @constructor Creates a new point.
 *
 * @since 09-July-2018
 * @author Damian Ryan
 */
data class Point(val x: Int, val y: Int): Comparable<Point> {

    init {
        if (x < 0 || y < 0) {
            // reject non-positive points - the origin (top left corner) of the drawing area is assumed to be (1, 1).
            throw IllegalArgumentException("Point cannot have negative coordinates")
        }
    }

    /**
     * Compares this point with another by X coordinate then Y coordinate.
     *
     * @param[other] Another point.
     * @return 0 if the points are the same, a negative integer if this point sorts before the other, or a positive
     * integer if this point sorts after the other.
     */
    override fun compareTo(other: Point): Int {
        return when (x) {
            other.x -> y - other.y
            else -> x - other.x
        }
    }
}
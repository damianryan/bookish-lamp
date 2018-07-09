package canvas

import canvas.Line.Orientation.HORIZONTAL
import canvas.Line.Orientation.VERTICAL
import kotlin.math.abs

/**
 * Line represents either a horizontal or vertical line as a collection of points.
 *
 * Diagonal lines are not (currently) supported but could be added if an implementation for calculating the points on a
 * diagonal line were to be added.
 *
 * @property origin The origin point of this line.
 * @property end The end point of this line.
 * @property length The length of this line.
 * @property orientation The orientation of this line - horizontal or vertical.
 * @constructor Creates a new line.
 *
 * @since 09-July-2018
 * @author Damian Ryan
 */
data class Line(val origin: Point, val end: Point): Pointy {

    init {
        if (origin.x != end.x && origin.y != end.y) {
            throw IllegalArgumentException("Lines must be horizontal or vertical, not diagonal")
        }
    }

    val length
        get() = when(orientation) {
            VERTICAL -> abs(origin.y - end.y) + 1 // X coords for start and end are same, length is difference in Y coords
            else -> abs(origin.x - end.x) + 1 // Y coords are the same, length is difference in X coords
        }

    val orientation
        get() = when(origin.x) {
            end.x -> VERTICAL
            else -> HORIZONTAL
        }

    override val points
        get() = when(orientation) {
            VERTICAL -> calculateVerticalLinePoints()
            else -> calculateHorizontalLinePoints()
        }

    /**
     * True if two lines share the same origin and end, or if one line's origin is the other's end, and vice versa.
     * So a line (1, 1)-(1, 10) matches another line (1, 1)-(1, 10) as well as a line (1, 10)-(1, 1).
     *
     * @return true if a line's origin and end match another's, even if the origin and end are switched.
     */
    override fun equals(other: Any?): Boolean {
        if (super.equals(other)) {
            return true
        } else {
            if (other is Line) {
                return origin == other.end && end == other.origin
            } else {
                return false
            }
        }
    }

    private fun calculateVerticalLinePoints(): List<Point> {
        val points = mutableListOf<Point>()
        for (y in earliestPoint.y..latestPoint.y) {
            points.add(Point(earliestPoint.x, y))
        }
        return points.toList() // makes the list immutable
    }

    private fun calculateHorizontalLinePoints(): List<Point> {
        val points = mutableListOf<Point>()
        for (x in earliestPoint.x..latestPoint.x) {
            points.add(Point(x, earliestPoint.y))
        }
        return points.toList() // makes the list immutable
    }

    private val earliestPoint
        get() = when(orientation) {
            VERTICAL -> if (origin.y > end.y) end else origin
            else -> if (origin.x > end.x) end else origin
        }

    private val latestPoint
        get() = when(orientation) {
            VERTICAL -> if (origin.y > end.y) origin else end
            else -> if (origin.x > end.x) origin else end
        }

    /**
     * Line orientation.
     *
     * @since 09-July-2017
     * @author Damian Ryan
     */
    enum class Orientation {

        /** Horizontal orientation */
        HORIZONTAL,

        /** Vertical orientation */
        VERTICAL
    }
}
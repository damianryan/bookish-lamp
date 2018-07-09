package canvas

/**
 * A rectangle with a specified origin (usually the top left corner) and end (usually the bottom right corner).
 *
 * @property origin The origin point of this rectangle.
 * @property end The end point of this rectangle.
 * @property lines The lines that form the borders of this rectangle.
 * @constructor creates a new rectangle.
 *
 * @since 09-July-2018
 * @author Damian Ryan
 */
data class Rectangle(val origin: Point, val end: Point): Pointy {

    val lines: List<Line>

    init {
        lines = listOf(
                Line(origin, Point(end.x, origin.y)),
                Line(origin, Point(origin.x, end.y)),
                Line(Point(origin.x, end.y), end),
                Line(Point(end.x, origin.y), end))
    }

    override val points: List<Point>
        get() = lines.flatMap { it.points } // flattened list of the points in each border line of this rectangle
}
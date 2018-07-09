package canvas

/**
 * Something that has [Point]s. So, something point-y.
 *
 * @property points The points in this point-y thing.
 *
 * @since 09-July-2018
 * @author Damian Ryan
 */
interface Pointy {
    val points: List<Point>
}
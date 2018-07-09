package canvas

/**
 * Parser parses command lines to return either a new [Canvas] drawable area of the specified dimensions, or a copy of
 * any existing drawable area updated to include the points of the most recently added line or rectangle.
 *
 * Valid commands are:
 *
 * * `C width height` - create a new drawable area of the specified dimensions.
 * * `L x1 y1 x2 y2` - draw either a horizontal or vertical line from (x1, y1) to (x2, y2). Diagonals not supported.
 * * `R x1 y1 x2 y2` - draw a rectangle, with top left corner at (x1, y1) and bottom right corner at (x2, y2).
 * * `Q` - quit the application.
 *
 * @constructor Creates a new parser.
 *
 * @since 09-July-2018
 * @author Damian Ryan
 */
class Parser {

    /**
     * Parses a command line in the context of any existing drawable area, or without context if no drawable area
     * yet exists.
     *
     * @param[args] A command line.
     */
    fun parse(args: String, canvas: Canvas? = null): Canvas? {
        val tokens = args.trim().split(" ")
        if (tokens.size < 1) {
            println("Syntax error: command must be one of C, L, R or Q")
            return canvas
        }
        val command = tokens[0]
        when (command) {
            "C" -> return newCanvas(canvas, tokens)
            "L" -> return addLine(canvas, tokens)
            "R" -> return addRectangle(canvas, tokens)
            "Q" -> System.exit(0)
            "" -> return canvas
            else -> println("Syntax error: command must be one of C, L, R or Q")
        }
        return canvas
    }

    // Creates a new drawable area by parsing the command line for width and height.
    private fun newCanvas(canvas: Canvas?, tokens: List<String>): Canvas? {
        if (tokens.size != 3) {
            println("Syntax error: C must have 2 positive integer arguments: width and height")
            return canvas
        }
        val errors = mutableListOf<String>()

        var width = -1
        var height = -1

        try {
            width = parseInt("width", tokens[1])
        } catch (x: NumberFormatException) {
            errors.add(x.message!!)
        }
        try {
            height = parseInt("height", tokens[2])
        } catch (x: NumberFormatException) {
            errors.add(x.message!!)
        }
        errors.forEach { println("error: ${it}") }
        if (errors.isNotEmpty()) {
            return null
        }
        return Canvas(width, height)
    }

    // Adds a new line with the specified start and end points to an existing drawable area.
    // Points falling outside the drawable area are discarded, so the line is effectively shortened to fit.
    // Note - this range checking should really be in the Canvas class itself, as a precondtion that either fails
    // or truncates, but I've left it here as an indicator of something that could be improved.
    private fun addLine(canvas: Canvas?, tokens: List<String>): Canvas? {
        if (null == canvas) {
            println("State error: cannot draw a line without a canvas")
            return null
        }
        if (tokens.size != 5) {
            println("Syntax error: L must have 4 positive integer arguments: x1, y1, x2 and x3")
            return canvas
        }

        val warnings = mutableListOf<String>()
        val errors = mutableListOf<String>()

        var originX = -1
        var originY = -1
        var endX = -1
        var endY = -1

        try {
            originX = parseInt("origin X coordinate", tokens[1])
        } catch (x: NumberFormatException) {
            errors.add(x.message!!)
        }
        try {
            originY = parseInt("origin Y coordinate", tokens[2])
        } catch (x: NumberFormatException) {
            errors.add(x.message!!)
        }
        try {
            endX = parseInt("end X coordinate", tokens[3])
        } catch (x: NumberFormatException) {
            errors.add(x.message!!)
        }
        try {
            endY = parseInt("end Y coordinate", tokens[4])
        } catch (x: NumberFormatException) {
            errors.add(x.message!!)
        }
        errors.forEach { println("error: ${it}") }
        if (errors.isNotEmpty()) {
            return canvas
        }

        if (originX > canvas.width) {
            originX = canvas.width
            warnings.add("origin X coordinate exceeded canvas width, was truncated")
        }
        if (originY > canvas.height) {
            originY = canvas.height
            warnings.add("origin Y coordinate exceeded canvas height, was truncated")
        }
        if (endX > canvas.width) {
            endX = canvas.width
            warnings.add("end X coordinate exceeded canvas width, was truncated")
        }
        if (endY > canvas.height) {
            endY = canvas.height
            warnings.add("end Y coordinate exceeded canvas height, was truncated")
        }
        warnings.forEach { println("warning: ${it}") }
        try {
            return canvas.addLine(Line(Point(originX, originY), Point(endX, endY)))
        } catch (x: RuntimeException) {
            println("error: ${x.message}")
            return canvas
        }
    }

    // Adds a rectangle with the specified diagonally-opposite corners to an existing drawable area. If the corners
    // fall outside the drawable area, they are shrunk in to fit within the drawable area.
    // Note - this range checking should really be in the Canvas class itself, as a precondtion that either fails
    // or truncates, but I've left it here as an indicator of something that could be improved.
    private fun addRectangle(canvas: Canvas?, tokens: List<String>): Canvas? {
        if (null == canvas) {
            println("State error: cannot draw a rectangle without a canvas")
            return null
        }
        if (tokens.size != 5) {
            println("Syntax error: R must have 4 positive integer arguments: x1, y1, x2 and x3")
            return canvas
        }

        val warnings = mutableListOf<String>()
        val errors = mutableListOf<String>()

        var originX = -1
        var originY = -1
        var endX = -1
        var endY = -1

        try {
            originX = parseInt("origin corner X coordinate", tokens[1])
        } catch (x: NumberFormatException) {
            errors.add(x.message!!)
        }
        try {
            originY = parseInt("origin corner Y coordinate", tokens[2])
        } catch (x: Exception) {
            errors.add(x.message!!)
        }
        try {
            endX = parseInt("end corner X coordinate", tokens[3])
        } catch (x: Exception) {
            errors.add(x.message!!)
        }
        try {
            endY = parseInt("end corner Y coordinate", tokens[4])
        } catch (x: Exception) {
            errors.add(x.message!!)
        }
        errors.forEach { println("error: ${it}") }
        if (errors.isNotEmpty()) {
            return canvas
        }

        if (originX > canvas.width) {
            originX = canvas.width
            warnings.add("origin corner X coordinate exceeded canvas width, was truncated")
        }
        if (originY > canvas.height) {
            originY = canvas.height
            warnings.add("origin corner Y coordinate exceeded canvas height, was truncated")
        }
        if (endX > canvas.width) {
            endX = canvas.width
            warnings.add("end corner X coordinate exceeded canvas width, was truncated")
        }
        if (endY > canvas.height) {
            endY = canvas.height
            warnings.add("end corner Y coordinate exceeded canvas height, was truncated")
        }
        warnings.forEach { println("warning: ${it}") }

        try {
            return canvas.addRectangle(Rectangle(Point(originX, originY), Point(endX, endY)))
        } catch (x: RuntimeException) {
            println("error: ${x.message}")
            return canvas
        }
    }

    // Parses a named string as an integer and checks that it is positive.
    private fun parseInt(name: String, string: String): Int {
        val result: Int
        try {
            result = string.toInt()
        } catch (x: NumberFormatException) {
            throw NumberFormatException("${name} '${string}' is not an integer")
        }
        if (result > 0) {
            return result
        } else {
            throw NumberFormatException("${name} '${string}' is not a positive integer")
        }
    }
}
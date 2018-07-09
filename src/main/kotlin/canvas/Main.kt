package canvas

/**
 * The entry point of the application.
 *
 * @param[args] Command line arguments (ignored).
 */
fun main(args: Array<String>) {
    var canvas: Canvas? = null
    val parser = Parser()
    while (true) {
        if (null != canvas) {
            println(canvas)
        }
        print("enter command: ")
        canvas = parser.parse(readLine()!!, canvas)
    }
}
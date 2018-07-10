# bookish-lamp [![Build Status](https://travis-ci.org/damianryan/bookish-lamp.svg?branch=master)](https://travis-ci.org/damianryan/bookish-lamp)

## Building bookish-lamp

bookish-lamp is built with gradle:

`./gradlew clean build` (*nix)

or

`gradlew.bat clean build` (windows)

If you are attempting a build on a machine that requires a web proxy to download dependencies, you can follow the 
instructions [here](https://docs.gradle.org/current/userguide/build_environment.html#sec:accessing_the_web_via_a_proxy).

If you need to use a proxying repository (such as a corporate Nexus server), you can follow the instructions 
[here](https://docs.gradle.org/current/userguide/declaring_repositories.html#sec:declaring_custom_repository) to
configure it by altering the `build.gradle` file of this project.

## Running bookish-lamp

Once bookish-lamp has been built, it can be run from the command line:

`java -jar build/libs/bookish-lamp-1.0-SNAPSHOT.jar` (*nix)

or

`java -jar build\libs\bookish-lamp-1.0-SNAPSHOT.jar` (windows)

This should display the following prompt:

`enter command: `

## Entering commands into bookish-lamp

bookish-lamp understands 4 commands:

* `C w h` - create a new canvas with the width `w` and height `h` (both arguments are mandatory & must be positive integers)
* `L x1 y1 x2 y2` - draw a line from `(x1, y1)` to `(x2, y2)` (all 4 arguments are mandatory & must be positive integers)
* `R x1 y1 x2 y2` - draw a rectangle whose top left corner is at `(x1, y1)` and bottom right corner is at `(x2, y2)` (all 4 arguments are mandatory & must be positive integers)
* `Q` - quit the application

If a line or rectangle will not fit within the bounds of the current canvas, it will not be added.

### Examples
    enter command: C 20 5
    ----------------------
    |                    |
    |                    |
    |                    |
    |                    |
    |                    |
    ----------------------
    enter command: L 1 3 7 3
    ----------------------
    |                    |
    |                    |
    |xxxxxxx             |
    |                    |
    |                    |
    ----------------------
    enter command: L 7 1 7 3
    ----------------------
    |      x             |
    |      x             |
    |xxxxxxx             |
    |                    |
    |                    |
    ----------------------
    enter command: R 15 2 20 5
    ----------------------
    |      x             |
    |      x       xxxxxx|
    |xxxxxxx       x    x|
    |              x    x|
    |              xxxxxx|
    ----------------------
    enter command: Q
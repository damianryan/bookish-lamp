package canvas;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class JPoint implements Comparable<JPoint>{

    private final int x;
    private final int y;

    public JPoint(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Point cannot have negative coordinates");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JPoint jPoint = (JPoint) o;
        return x == jPoint.x &&
                y == jPoint.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(@NotNull JPoint o) {
        return (x == o.x) ? y - o.y : x - o.x;
    }
}

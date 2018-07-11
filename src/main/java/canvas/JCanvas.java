package canvas;

import java.util.*;

public class JCanvas {

    private final int width;
    private final int height;
    private final Set<JPoint> points;

    public JCanvas(int width, int height) {
        this(width, height, Collections.unmodifiableSet(new HashSet<>()));
    }

    public JCanvas(int width, int height, Set<JPoint> points) {
        this.width = width;
        this.height = height;
        this.points = points;
    }

    public JCanvas addPointyThing(JPointy thing) {
        if (canFit(thing)) {
            return copy(addNewPoints(thing.getPoints()));
        } else {
            throw new IllegalArgumentException("Pointy thing does not fit within bounds of this drawable area");
        }
    }

    private boolean canFit(JPointy thing) {
        return thing.getPoints().stream().noneMatch(p -> p.getY() > height || p.getX() > width);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Set<JPoint> getPoints() {
        return points;
    }

    private Set<JPoint> addNewPoints(Collection<JPoint> newPoints) {
        Set<JPoint> basePoints = new HashSet<>();
        basePoints.addAll(points);
        basePoints.addAll(newPoints);
        return Collections.unmodifiableSet(basePoints);
    }

    public JCanvas copy(Set<JPoint> newPoints) {
        return new JCanvas(width, height, newPoints);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JCanvas jCanvas = (JCanvas) o;
        return width == jCanvas.width &&
                height == jCanvas.height &&
                Objects.equals(points, jCanvas.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, points);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= width + 2; i++) {
            s.append('-');
        }
        s.append('\n');
        for (int y = 1; y <= height; y++) {
            s.append('|');
            for (int x = 1; x <= width; x++) {
                s.append(points.contains(new JPoint(x, y)) ? 'x' : ' ');
            }
            s.append("|\n");
        }
        for (int i = 1; i <= width + 2; i++) {
            s.append('-');
        }
        return s.toString();
    }
}

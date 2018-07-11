package canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JLine implements JPointy {

    private final JPoint origin;
    private final JPoint end;

    public JLine(JPoint origin, JPoint end) {
        if (origin.getX() != end.getX() && origin.getY() != end.getY()) {
            throw new IllegalArgumentException("Lines must be horizontal or vertical, not diagonal");
        }
        this.origin = origin;
        this.end = end;
    }

    public JPoint getOrigin() {
        return origin;
    }

    public JPoint getEnd() {
        return end;
    }

    public int getLength() {
        switch(getOrientation()) {
            case VERTICAL: return Math.abs(origin.getY() - end.getY()) + 1;
            default: return Math.abs(origin.getX() - end.getX()) + 1;
        }
    }

    public Orientation getOrientation() {
        return origin.getX() == end.getX() ? Orientation.VERTICAL : Orientation.HORIZONTAL;
    }

    @Override
    public List<JPoint> getPoints() {
        switch(getOrientation()) {
            case VERTICAL: return calculateVerticalLinePoints();
            default: return calculateHorizontalLinePoints();
        }
    }

    private List<JPoint> calculateVerticalLinePoints() {
        List<JPoint> points = new ArrayList<>();
        for (int y = earliestPoint().getY(); y <= latestPoint().getY(); y++) {
            points.add(new JPoint(earliestPoint().getX(), y));
        }
        return Collections.unmodifiableList(points);
    }

    private List<JPoint> calculateHorizontalLinePoints() {
        List<JPoint> points = new ArrayList<>();
        for (int x = earliestPoint().getX(); x <= latestPoint().getX(); x++) {
            points.add(new JPoint(x, earliestPoint().getY()));
        }
        return Collections.unmodifiableList(points);
    }

    private JPoint earliestPoint() {
        switch(getOrientation()) {
            case VERTICAL: return origin.getY() > end.getY() ? end : origin;
            default: return origin.getX() > end.getX() ? end : origin;
        }
    }

    private JPoint latestPoint() {
        switch(getOrientation()) {
            case VERTICAL: return origin.getY() > end.getY() ? origin : end;
            default: return origin.getX() > end.getX() ? origin : end;
        }
    }

    enum Orientation {
        HORIZONTAL, VERTICAL
    }
}

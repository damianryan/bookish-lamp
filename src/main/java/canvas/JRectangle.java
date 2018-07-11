package canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class JRectangle implements JPointy {

    private final JPoint origin;
    private final JPoint end;
    private final List<JLine> lines;

    public JRectangle(JPoint origin, JPoint end) {
        this.origin = origin;
        this.end = end;
        List<JLine> lines = new ArrayList<>();
        lines.add(new JLine(origin, new JPoint(end.getX(), origin.getY())));
        lines.add(new JLine(origin, new JPoint(origin.getX(), end.getY())));
        lines.add(new JLine(new JPoint(origin.getX(), end.getY()), end));
        lines.add(new JLine(new JPoint(end.getX(), origin.getY()), end));
        this.lines = Collections.unmodifiableList(lines);
    }

    @Override
    public List<JPoint> getPoints() {
        List<JPoint> allPoints = new ArrayList<>();
        lines.forEach(l -> allPoints.addAll(l.getPoints()));
        return Collections.unmodifiableList(allPoints);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JRectangle that = (JRectangle) o;
        return Objects.equals(origin, that.origin) &&
                Objects.equals(end, that.end) &&
                Objects.equals(lines, that.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, end, lines);
    }
}

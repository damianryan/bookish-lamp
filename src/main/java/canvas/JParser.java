package canvas;

import java.util.ArrayList;
import java.util.List;

public class JParser {

    public JCanvas parse(String args, JCanvas canvas) {
        String[] tokens = args.trim().split(" ");
        if (tokens.length < 1) {
            System.out.println("Syntax error: command must be one of C, L, R or Q");
            return canvas;
        }
        String command = tokens[0];
        switch (command) {
            case "C": return newCanvas(canvas, tokens);
            case "L":
            case "R": return addPointyThing(canvas, tokens);
            case "Q": System.exit(0);
            case "" : return canvas;
            default: System.out.println("Syntax error: command must be one of C, L, R or Q");
        }
        return canvas;
    }

    private JCanvas newCanvas(JCanvas canvas, String[] tokens) {
        if (tokens.length != 3) {
            System.out.println("Syntax error: C must have 2 positive integer arguments: width and height");
            return canvas;
        }
        List<String> errors = new ArrayList<>();

        int width = -1;
        int height = -1;

        try {
            width = parseInt("width", tokens[1]);
        } catch (NumberFormatException x) {
            errors.add(x.getMessage());
        }
        try {
            height = parseInt("height", tokens[2]);
        } catch (NumberFormatException x) {
            errors.add(x.getMessage());
        }
        errors.forEach(System.out::println);
        if (!errors.isEmpty()) {
            return null;
        }
        return new JCanvas(width, height);
    }

    private JCanvas addPointyThing(JCanvas canvas, String[] tokens) {
        if (null == canvas) {
            System.out.println("State error: cannot draw anything without a canvas");
            return null;
        }
        if (tokens.length != 5) {
            System.out.printf("Syntax error: %s must have 4 positive integer arguments: x1, y1, x2 and x3\n", tokens[0]);
            return canvas;
        }
        List<String> errors = new ArrayList<>();

        int originX = -1;
        int originY = -1;
        int endX = -1;
        int endY = -1;

        try {
            originX = parseInt("origin X coordinate", tokens[1]);
        } catch (NumberFormatException x) {
            errors.add(x.getMessage());
        }
        try {
            originY = parseInt("origin Y coordinate", tokens[2]);
        } catch (NumberFormatException x) {
            errors.add(x.getMessage());
        }
        try {
            endX = parseInt("end X coordinate", tokens[3]);
        } catch (NumberFormatException x) {
            errors.add(x.getMessage());
        }
        try {
            endY = parseInt("end Y coordinate", tokens[4]);
        } catch (NumberFormatException x) {
            errors.add(x.getMessage());
        }
        errors.forEach(System.out::println);
        if (!errors.isEmpty()) {
            return canvas;
        }

        try {
            JPointy thing;
            switch(tokens[0]) {
                case "L":
                    thing = new JLine(new JPoint(originX, originY), new JPoint(endX, endY));
                    break;
                default:
                    thing = new JRectangle(new JPoint(originX, originY), new JPoint(endX, endY));
            }
            return canvas.addPointyThing(thing);
        } catch (RuntimeException x) {
            System.out.printf("error: %s\n", x.getMessage());
            return canvas;
        }
    }

    private int parseInt(String name, String string) {
        int result;
        try {
            result = Integer.parseInt(string);
        } catch (NumberFormatException x) {
            throw new NumberFormatException(String.format("%s '%s' is not an integer", name, string));
        }
        if (result > 0) {
            return result;
        } else {
            throw new NumberFormatException(String.format("%s '%s' is not a positive integer", name, string));
        }
    }
}

package canvas;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        JCanvas canvas = null;
        JParser parser = new JParser();
        Scanner reader = new Scanner(System.in);
        while (true) {
            if (null != canvas) {
                System.out.println(canvas);
            }
            System.out.print("enter command: ");
            canvas = parser.parse(reader.nextLine(), canvas);
        }
    }
}

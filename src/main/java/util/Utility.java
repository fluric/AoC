package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utility {
    public static List<String> readLines(String path) {
        var file = new File("src/main/resources/" + path + ".txt");

        try {
            var scanner = new Scanner(file);
            var lines = new ArrayList<String>();

            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
            return lines;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void check(int expected, int actual) {
        if (expected != actual) {
            throw new RuntimeException("Expected %s but got %s".formatted(expected, actual));
        }
    }

    public static void check(long expected, long actual) {
        if (expected != actual) {
            throw new RuntimeException("Expected %s but got %s".formatted(expected, actual));
        }
    }
}

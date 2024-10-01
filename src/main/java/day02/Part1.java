package day02;

import util.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {

    public static void main(String[] args) {
        var testInput = Utility.readLines("day02/test_1");
        Utility.check(8, solve(testInput));

        var input = Utility.readLines("day02/input");
        System.out.println(solve(input));
    }

    private static int solve(List<String> input) {
        return input
                .stream()
                .map(i -> i.substring(5)).filter(Part1::isPossible)
                .mapToInt(i -> Integer.parseInt(i.split(":")[0]))
                .sum();
    }

    private static boolean isPossible(String input) {
        var cut = input.split(":")[1];
        return Arrays.stream(cut.split(";")).allMatch(Part1::isDrawPossible);
    }

    private static boolean isDrawPossible(String draw) {
        return Arrays.stream(draw.split(",")).allMatch(Part1::isColorPossible);
    }

    private static boolean isColorPossible(String color) {

        if (color.contains("red")) {
            var number = Integer.parseInt(color.replace("red", "").trim());
            return number <= 12;
        }
        if (color.contains("blue")) {
            var number = Integer.parseInt(color.replace("blue", "").trim());
            return number <= 14;
        }
        if (color.contains("green")) {
            var number = Integer.parseInt(color.replace("green", "").trim());
            return number <= 13;
        }
        throw new IllegalArgumentException("Wrong color");
    }
}

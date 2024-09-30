package day01;

import util.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Part2 {

    private final static Map<String, Integer> DIGIT_MAP = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9);

    public static void main(String[] args) {
        var testInput = Utility.readLines("day01/test_2");
        Utility.check(281, solve(testInput));

        var input = Utility.readLines("day01/input");
        System.out.println(solve(input));
    }

    private static int solve(List<String> input) {
        return input.stream().mapToInt(Part2::solve).sum();
    }

    private static int solve(String input) {
        var digits = new ArrayList<Integer>();
        for (int i = 0; i < input.length(); i++) {
            input = modifyInput(input, i);

            var c = input.charAt(i);
            if (Character.isDigit(c)) {
                digits.add(Character.digit(c, 10));
            }
        }

        return digits.getFirst() * 10 + digits.getLast();
    }

    private static String modifyInput(String input, int i) {
        for (var digit : DIGIT_MAP.keySet()) {
            if (input.startsWith(digit, i)) {
                input = input.substring(0, i) + DIGIT_MAP.get(digit) + input.substring(i + 1);
            }
        }
        return input;
    }
}

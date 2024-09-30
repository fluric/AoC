package day01;

import util.Utility;

import java.util.ArrayList;
import java.util.List;

public class Part1 {

    public static void main(String[] args) {
        var testInput = Utility.readLines("day01/test_1");
        Utility.check(142, solve(testInput));

        var input = Utility.readLines("day01/input");
        System.out.println(solve(input));
    }

    private static int solve(List<String> input) {
        return input.stream().mapToInt(Part1::solve).sum();
    }

    private static int solve(String input) {
        var digits = new ArrayList<Integer>();
        for (int i = 0; i < input.length(); i++) {
            var c = input.charAt(i);
            if (Character.isDigit(c)) {
               digits.add(Character.digit(c, 10));
            }
        }

        return digits.getFirst() * 10 + digits.getLast();
    }
}

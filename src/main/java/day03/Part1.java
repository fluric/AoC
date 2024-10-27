package day03;

import util.Utility;

import java.util.List;

public class Part1 {

    public static void main(String[] args) {
        var testInput = Utility.readLines("day03/test_1");
        Utility.check(4361, solve(testInput));

        var input = Utility.readLines("day03/input");
        System.out.println(solve(input));
    }

    private static int solve(List<String> input) {
        var sum = 0;

        for (int i = 0; i < input.size(); i++) {
            sum += solveLine(input, i, input.get(i));
        }
        return sum;
    }

    private static int solveLine(List<String> input, int x, String line) {
        var sum = 0;
        var number = "";
        var adjacent = false;

        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                number = number + line.charAt(i);
                adjacent = adjacent || isAdjacent(input, x, i);
            }
            else {
                if (adjacent && !number.isBlank()) {
                    sum += Integer.parseInt(number);
                }
                adjacent = false;
                number = "";
            }
        }

        if (adjacent && !number.isBlank()) {
            sum += Integer.parseInt(number);
        }

        return sum;
    }

    private static boolean isAdjacent(List<String> input, int x, int y) {
        var above = x > 0 ? input.get(x - 1) : null;
        var below = x < input.size() - 1 ? input.get(x + 1) : null;

        if (above != null && isSymbol(above.charAt(y))) {
            return true;
        }
        if (below != null && isSymbol(below.charAt(y))) {
            return true;
        }

        if (y > 0) {
            if (isSymbol(input.get(x).charAt(y - 1))) {
                return true;
            }
            if (above != null && isSymbol(above.charAt(y - 1))) {
                return true;
            }
            if (below != null && isSymbol(below.charAt(y - 1))) {
                return true;
            }
        }

        if (y < input.get(x).length() - 1) {
            if (isSymbol(input.get(x).charAt(y + 1))) {
                return true;
            }
            if (above != null && isSymbol(above.charAt(y + 1))) {
                return true;
            }
            if (below != null && isSymbol(below.charAt(y + 1))) {
                return true;
            }
        }

        return false;
    }

    private static boolean isSymbol(char a) {
        return !Character.isDigit(a) && a != '.';
    }
}

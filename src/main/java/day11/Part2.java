package day11;

import util.Utility;

import java.util.List;

public class Part2 {

    public static void main(String[] args) {
        var testInput = Utility.readLines("day11/test_1");
        Utility.check(374, solve(testInput, 2));
        Utility.check(1030, solve(testInput, 10));
        Utility.check(8410, solve(testInput, 100));

        var input = Utility.readLines("day11/input");
        System.out.println(solve(input, 1_000_000));
    }

    private static long solve(List<String> input, int gravitationalFactor) {
        var galaxies = input.stream().flatMapToInt(String::chars).filter(c -> c == '#').count();

        var sum = 0L;

        // Iterate rows
        var galaxiesOnLeft = 0L;
        for (var row : input) {
            var galaxiesInRow = row.chars().filter(c -> c == '#').count();
            var factor = galaxiesInRow == 0 ? gravitationalFactor : 1; // Gravitational effect
            var galaxiesOnRight = galaxies - galaxiesOnLeft;
            sum += factor * galaxiesOnLeft * galaxiesOnRight;
            galaxiesOnLeft += galaxiesInRow;
        }

        // Iterate columns
        var galaxiesOnTop = 0L;
        for (var col = 0; col < input.getFirst().length(); col++) {
            int finalCol = col;
            var galaxiesInCol = input.stream().map(i -> i.charAt(finalCol)).filter(c -> c == '#').count();
            var factor = galaxiesInCol == 0 ? gravitationalFactor : 1; // Gravitational effect
            var galaxiesOnBottom = galaxies - galaxiesOnTop;
            sum += factor * galaxiesOnTop * galaxiesOnBottom;
            galaxiesOnTop += galaxiesInCol;
        }

        return sum;
    }

}

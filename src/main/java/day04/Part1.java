package day04;

import util.Utility;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class Part1 {

    public static void main(String[] args) {
        var testInput = Utility.readLines("day04/test_1");
        Utility.check(13, solve(testInput));

        var input = Utility.readLines("day04/input");
        System.out.println(solve(input));
    }

    private static int solve(List<String> input) {
        return input.stream().mapToInt(Part1::solveLine).sum();
    }

    private static int solveLine(String line) {
        var withoutPrefix = line.split(":")[1].trim();
        var winningNumbers = Arrays.stream(withoutPrefix.split("\\|")[0].trim().split(" "))
                .filter(not(String::isBlank))
                .map(Integer::parseInt)
                .toList();
        var ownNumbers = Arrays.stream(withoutPrefix.split("\\|")[1].trim().split(" "))
                .filter(not(String::isBlank))
                .map(Integer::parseInt)
                .toList();

        var count = ownNumbers.stream().filter(winningNumbers::contains).count();

        return 1 << (count - 1);
    }
}

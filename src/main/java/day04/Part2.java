package day04;

import util.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class Part2 {

    public static void main(String[] args) {
        var testInput = Utility.readLines("day04/test_2");
        Utility.check(30, solve(testInput));

        var input = Utility.readLines("day04/input");
        System.out.println(solve(input));
    }

    private static int solve(List<String> input) {
        var matchesList = input.stream().map(Part2::solveLine).toList();
        var cardsCount = Stream.generate(() -> 1).limit(input.size()).mapToInt(Integer::intValue).toArray();

        for (var i = 0; i < input.size(); i++) {
            var matches = matchesList.get(i);

            for (var j = i + 1; j < input.size() && j < i + matches + 1; j++) {
                cardsCount[j] += cardsCount[i];
            }
        }

        return Arrays.stream(cardsCount).sum();
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

        return (int) ownNumbers.stream().filter(winningNumbers::contains).count();
    }
}

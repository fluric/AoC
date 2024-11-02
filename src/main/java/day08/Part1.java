package day08;

import kotlin.Pair;
import util.Utility;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Part1 {

    public static void main(String[] args) {
        var testInput = Utility.readLines("day08/test_1");
        Utility.check(2, solve(testInput));

        var testInput2 = Utility.readLines("day08/test_2");
        Utility.check(6, solve(testInput2));

        var input = Utility.readLines("day08/input");
        System.out.println(solve(input));
    }

    private static int solve(List<String> input) {
        var sequence = input.getFirst().chars().boxed().toList();

        var lookUp = input.stream().skip(2).collect(Collectors.toMap(Part1::getKey, Part1::getValue));

        var counter = 0;
        var position = "AAA";
        while(true) {
            for (var instruction : sequence) {
                counter++;
                var value = lookUp.get(position);
                position = instruction == 'L' ? value.getFirst() : value.getSecond();

                if (position.equals("ZZZ")) {
                    return counter;
                }
            }
        }
    }

    private static String getKey(String line) {
        return line.split(" =")[0];
    }

    private static Pair<String, String> getValue(String line) {
        var left = line.split("\\(")[1].split(",")[0];
        var right = line.split(", ")[1].replace(")", "");

        return new Pair<>(left, right);
    }
}

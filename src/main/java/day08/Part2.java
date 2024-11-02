package day08;

import kotlin.Pair;
import util.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args) {
        var testInput = Utility.readLines("day08/test_3");
        Utility.check(6, solve(testInput));

        var input = Utility.readLines("day08/input");
        System.out.println(solve(input));
    }

    private static int solve(List<String> input) {
        var sequence = input.getFirst().chars().boxed().toList();

        var lookUp = input.stream().skip(2).collect(Collectors.toMap(Part2::getKey, Part2::getValue));

        var positions = new ArrayList<>(lookUp.keySet().stream().filter(p -> p.charAt(2) == 'A').toList());
        var counter = 0;
        while(true) {
            for (var instruction : sequence) {
                counter++;
                for (var i = 0; i < positions.size(); i++) {
                    var position = positions.get(i);
                    var value = lookUp.get(position);
                    positions.set(i, instruction == 'L' ? value.getFirst() : value.getSecond());
                }

                if (positions.stream().allMatch(p -> p.charAt(2) == 'Z')) {
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

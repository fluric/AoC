package day05;

import util.Utility;

import java.util.Arrays;
import java.util.List;

public class Part1 {

    public static void main(String[] args) {
        var testInput = Utility.readLines("day05/test_1");
        Utility.check(35, (int) solve(testInput));

        var input = Utility.readLines("day05/input");
        System.out.println(solve(input));
    }

    private static long solve(List<String> input) {
        var seeds = Arrays.stream(input.getFirst().split(": ")[1].split(" ")).map(Long::parseLong).toList();
        var trimmedInput = input.subList(3, input.size());

        return seeds.stream().mapToLong(s -> getSeedLocation(s, trimmedInput)).min().orElseThrow();
    }

    private static long getSeedLocation(long seed, List<String> input) {
        var currentValue = seed;

        for (int i = 0; i < input.size(); i++) {
            var line = input.get(i);

            if (line.isBlank()) {
                i++;
                continue;
            }

            var destination = Long.parseLong(line.split(" ")[0]);
            var source = Long.parseLong(line.split(" ")[1]);
            var range = Long.parseLong(line.split(" ")[2]);

            if (currentValue >= source && currentValue <= source + range) {
                currentValue += destination - source;
                while (i + 1 < input.size() && !input.get(i + 1).isBlank()) {
                    i++;
                }
            }
        }

        return currentValue;
    }
}

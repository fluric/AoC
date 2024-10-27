package day05;

import util.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Part2 {

    public static void main(String[] args) {
        var testInput = Utility.readLines("day05/test_2");
        Utility.check(46, (int) solve(testInput));

        var input = Utility.readLines("day05/input");
        System.out.println(solve(input));
    }

    private static long solve(List<String> input) {
        var seedInput = Arrays.stream(input.getFirst().split(": ")[1].split(" ")).map(Long::parseLong).toList();

        var trimmedInput = input.subList(3, input.size());
        var min = Long.MAX_VALUE;
        for (int i = 0; i < seedInput.size(); i += 2) {
            var localMin = LongStream.range(seedInput.get(i), seedInput.get(i + 1) + seedInput.get(i))
                    .map(s -> getSeedLocation(s, trimmedInput))
                    .min()
                    .orElseThrow();

            min = Math.min(min, localMin);
        }

        return min;
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

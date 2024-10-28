package day06;

import util.Utility;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;

public class Part1 {

    public static void main(String[] args) {
        var testInput = Utility.readLines("day06/test_1");
        Utility.check(288, solve(testInput));

        var input = Utility.readLines("day06/input");
        System.out.println(solve(input));
    }

    private static int solve(List<String> input) {
        var times = Arrays.stream(input.get(0).split(":")[1].trim().split(" ")).filter(not(String::isBlank)).map(Integer::parseInt).toList();
        var distances = Arrays.stream(input.get(1).split(":")[1].trim().split(" ")).filter(not(String::isBlank)).map(Integer::parseInt).toList();

        int factor = 1;
        for (int i = 0; i < times.size(); i++) {
            var time = times.get(i);
            var distance = distances.get(i);

            factor *= (int) IntStream.range(1, time).map(p -> getDistance(time, p)).filter(d -> d > distance).count();
        }
        return factor;
    }

    private static int getDistance(int time, int push) {
        return (time - push) * push;
    }
}

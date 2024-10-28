package day06;

import kotlin.Pair;
import util.Utility;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;

public class Part2 {

    public static void main(String[] args) {
        var testInput = Utility.readLines("day06/test_1");
        Utility.check(71503, solve(testInput));

        var input = Utility.readLines("day06/input");
        System.out.println(solve(input));
    }

    private static int solve(List<String> input) {
        var time = Long.parseLong(input.get(0).split(":")[1].trim().replace(" ", ""));
        var distance = Long.parseLong(input.get(1).split(":")[1].trim().replace(" ", ""));

        var pair = solveQuadraticEquation(-1, time, -distance);

        return (int) (Math.floor(pair.getFirst()) - Math.ceil(pair.getSecond())) + 1;
    }

    private static Pair<Double, Double> solveQuadraticEquation(long a, long b, long c) {
        double sqrt = Math.sqrt(b * b - 4 * a * c);
        var lower = (-b - sqrt) / 2 / a;
        var upper = (-b + sqrt) / 2 / a;

        return new Pair<>(lower, upper);
    }

    // (time - push) * push >= distance -> -push^2 + time * push - distance >= 0
    // Either quadratic equation or logarithmic search of lower and upper bound
}

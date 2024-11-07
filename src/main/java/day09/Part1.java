package day09;

import util.MathUtil;
import util.Utility;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Part1 {

    public static void main(String[] args) {
        var testInput = Utility.readLines("day09/test_1");
        Utility.check(114, solve(testInput));

        var input = Utility.readLines("day09/input");
        System.out.println(solve(input));
    }

    private static long solve(List<String> input) {
        return input.stream()
                .map(i -> Arrays.stream(i.split(" ")).map(Long::parseLong).toList())
                .mapToLong(Part1::extrapolate)
                .sum();
    }

    private static long extrapolate(List<Long> numbers) {
        var n = numbers.size();

        var pascalCoefficients = IntStream.range(0, n).boxed().map(i -> MathUtil.getPascalCoefficient(n, i)).toList();

        var sum = 0L;
        var factor = n % 2 == 0 ? 1L : -1L;
        for (int i = 0; i < numbers.size(); i++) {
            factor *= -1;
            sum += factor * pascalCoefficients.get(i) * numbers.get(i);
        }

        return sum;
    }

}

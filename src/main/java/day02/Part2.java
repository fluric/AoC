package day02;

import util.Utility;

import java.util.Arrays;
import java.util.List;

public class Part2 {

    public static void main(String[] args) {
        var testInput = Utility.readLines("day02/test_2");
        Utility.check(2286, solve(testInput));

        var input = Utility.readLines("day02/input");
        System.out.println(solve(input));
    }

    private static int solve(List<String> input) {
        return input
                .stream()
                .map(i -> i.substring(5))
                .mapToInt(Part2::getPower)
                .sum();
    }

    private static int getPower(String input) {
        var cut = input.split(":")[1];
        var draws = cut.split(";");

        var red = Arrays.stream(draws).mapToInt(d -> getColorFromDraw(d, "red")).max().orElse(0);
        var blue = Arrays.stream(draws).mapToInt(d -> getColorFromDraw(d, "blue")).max().orElse(0);
        var green = Arrays.stream(draws).mapToInt(d -> getColorFromDraw(d, "green")).max().orElse(0);

        return red * blue * green;
    }

    private static int getColorFromDraw(String draw, String color) {
        return Arrays.stream(draw.split(",")).mapToInt(d -> getColor(d, color)).max().orElse(0);
    }

    private static int getColor(String amount, String color) {
        return amount.contains(color)
                ? Integer.parseInt(amount.replace(color, "").trim())
                : 0;
    }
}

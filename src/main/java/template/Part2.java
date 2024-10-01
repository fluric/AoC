package template;

import util.Utility;

import java.util.List;

public class Part2 {
    public static void main(String[] args) {
        var testInput = Utility.readLines("day0x/test_2");
        Utility.check(0, solve(testInput));

        var input = Utility.readLines("day0x/input");
        System.out.println(solve(input));
    }

    private static int solve(List<String> input) {
        return 0;
    }

}

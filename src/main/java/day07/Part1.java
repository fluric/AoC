package day07;

import kotlin.Pair;
import util.Utility;

import java.util.*;
import java.util.stream.Collectors;

public class Part1 {

    private enum Type {
        FIVE_OF_KIND,
        FOUR_OF_KIND,
        FULL_HOUSE,
        THREE_OF_KIND,
        TWO_PAIR,
        ONE_PAIR,
        HIGH_CARD

    }

    private final static Map<Character, Integer> CARD_TO_VALUE =  new HashMap<>();
    static {
        CARD_TO_VALUE.put('A', 14);
        CARD_TO_VALUE.put('K', 13);
        CARD_TO_VALUE.put('Q', 12);
        CARD_TO_VALUE.put('J', 11);
        CARD_TO_VALUE.put('T', 10);
        CARD_TO_VALUE.put('9', 9);
        CARD_TO_VALUE.put('8', 8);
        CARD_TO_VALUE.put('7', 7);
        CARD_TO_VALUE.put('6', 6);
        CARD_TO_VALUE.put('5', 5);
        CARD_TO_VALUE.put('4', 4);
        CARD_TO_VALUE.put('3', 3);
        CARD_TO_VALUE.put('2', 2);
    }

    public static void main(String[] args) {
        var testInput = Utility.readLines("day07/test_1");
        Utility.check(6440, solve(testInput));

        var input = Utility.readLines("day07/input");
        System.out.println(solve(input));
    }

    private static long solve(List<String> input) {
        var handAndStrengthList = input.stream()
                .map(i -> i.split(" "))
                .map(s -> new Pair<>(s[0], Integer.parseInt(s[1])))
                .sorted(Comparator.comparing(p -> getType(((Pair<String, Integer>) p).getFirst()).ordinal()).reversed()
                        .thenComparing(p -> CARD_TO_VALUE.get(((Pair<String, Integer>) p).getFirst().charAt(0)))
                        .thenComparing(p -> CARD_TO_VALUE.get(((Pair<String, Integer>) p).getFirst().charAt(1)))
                        .thenComparing(p -> CARD_TO_VALUE.get(((Pair<String, Integer>) p).getFirst().charAt(2)))
                        .thenComparing(p -> CARD_TO_VALUE.get(((Pair<String, Integer>) p).getFirst().charAt(3)))
                        .thenComparing(p -> CARD_TO_VALUE.get(((Pair<String, Integer>) p).getFirst().charAt(4))))
                .toList();

        var sum = 0L;

        for (int i = handAndStrengthList.size(); i > 0; i--) {
            sum += (long) handAndStrengthList.get(i - 1).getSecond() * i;
        }

        return sum;
    }

    private static Type getType(String hand) {
        Map<Integer, Integer> charsCount = "AKQJT98765432".chars().boxed().collect(Collectors.toMap(c -> c, c -> 0));
        hand.chars().boxed().forEach(c -> charsCount.put(c, charsCount.get(c) + 1));

        var occurrences = charsCount.values();
        if (occurrences.stream().anyMatch(v -> v == 5)) {
            return Type.FIVE_OF_KIND;
        }
        if (occurrences.stream().anyMatch(v -> v == 4)) {
            return Type.FOUR_OF_KIND;
        }
        if (occurrences.stream().anyMatch(v -> v == 3) && occurrences.stream().anyMatch(v -> v == 2)) {
            return Type.FULL_HOUSE;
        }
        if (occurrences.stream().anyMatch(v -> v == 3)) {
            return Type.THREE_OF_KIND;
        }
        if (occurrences.stream().filter(v -> v == 2).count() == 2) {
            return Type.TWO_PAIR;
        }
        if (occurrences.stream().anyMatch(v -> v == 2)) {
            return Type.ONE_PAIR;
        }
        return Type.HIGH_CARD;
    }

}

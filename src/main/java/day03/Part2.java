package day03;

import util.Utility;

import java.util.*;

public class Part2 {

    public static void main(String[] args) {
        var testInput = Utility.readLines("day03/test_2");
        Utility.check(467835, solve(testInput));

        var input = Utility.readLines("day03/input");
        System.out.println(solve(input));
    }

    private static int solve(List<String> input) {
        var sum = 0;
        var adjacencyMap = new HashMap<String, List<Integer>>();

        for (int i = 0; i < input.size(); i++) {
            solveLine(input, i, input.get(i), adjacencyMap);
        }

        for (var entry : adjacencyMap.entrySet()) {
            if (entry.getValue().size() == 2) {
                sum += entry.getValue().get(0) * entry.getValue().get(1);
            }
        }
        return sum;
    }

    private static void solveLine(List<String> input, int x, String line, Map<String, List<Integer>> adjacencyMap) {
        var number = "";
        var adjacentStars = new HashSet<String>();

        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                number = number + line.charAt(i);
                if (isAdjacentToStar(input, x, i) != null) {
                    adjacentStars.add(isAdjacentToStar(input, x, i));
                }
            }
            else {
                if (!adjacentStars.isEmpty() && !number.isBlank()) {
                    populateAdjacencyMap(adjacentStars, Integer.parseInt(number), adjacencyMap);
                }
                adjacentStars = new HashSet<>();
                number = "";
            }
        }

        if (!adjacentStars.isEmpty() && !number.isBlank()) {
            populateAdjacencyMap(adjacentStars, Integer.parseInt(number), adjacencyMap);
        }
    }

    private static void populateAdjacencyMap(Set<String> adjacencies, int number, Map<String, List<Integer>> adjacencyMap) {
        for (var adjacency : adjacencies) {
            if (adjacencyMap.get(adjacency) != null) {
                adjacencyMap.get(adjacency).add(number);
            }
            else {
                var list = new ArrayList<Integer>();
                list.add(number);
                adjacencyMap.put(adjacency, list);
            }
        }
    }

    private static String isAdjacentToStar(List<String> input, int x, int y) {
        var above = x > 0 ? input.get(x - 1) : null;
        var below = x < input.size() - 1 ? input.get(x + 1) : null;

        if (above != null && isSymbol(above.charAt(y))) {
            return x - 1 + "_" + y;
        }
        if (below != null && isSymbol(below.charAt(y))) {
            return x + 1 + "_" + y;
        }

        if (y > 0) {
            if (isSymbol(input.get(x).charAt(y - 1))) {
                return x + "_" + (y - 1);
            }
            if (above != null && isSymbol(above.charAt(y - 1))) {
                return x - 1 + "_" + (y - 1);
            }
            if (below != null && isSymbol(below.charAt(y - 1))) {
                return x + 1 + "_" + (y - 1);
            }
        }

        if (y < input.get(x).length() - 1) {
            if (isSymbol(input.get(x).charAt(y + 1))) {
                return x + "_" + (y + 1);
            }
            if (above != null && isSymbol(above.charAt(y + 1))) {
                return x - 1 + "_" + (y + 1);
            }
            if (below != null && isSymbol(below.charAt(y + 1))) {
                return x + 1 + "_" + (y + 1);
            }
        }

        return null;
    }

    private static boolean isSymbol(char a) {
        return a == '*';
    }
}

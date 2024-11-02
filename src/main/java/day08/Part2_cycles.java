package day08;

import kotlin.Pair;
import util.Utility;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Part2_cycles {

    private record CycleInfo(Long offSet, Long cycleNumber, List<Long> ends) {
    }

    public static void main(String[] args) {
        var testInput = Utility.readLines("day08/test_3");
        Utility.check(6, solve(testInput));

        var input = Utility.readLines("day08/input");
        System.out.println(solve(input));
    }

    private static long solve(List<String> input) {
        var sequence = input.getFirst().chars().boxed().toList();

        var lookUp = input.stream().skip(2).collect(Collectors.toMap(Part2_cycles::getKey, Part2_cycles::getValue));

        var positions = new ArrayList<>(lookUp.keySet().stream().filter(p -> p.charAt(2) == 'A').toList());
        var cycleInfoList = positions.stream().map(p -> getCycle(p, sequence, lookUp)).toList();
        var firstCycleInfo = cycleInfoList.getFirst();
        var counter = firstCycleInfo.offSet();

        while (true) {
            for (var end : firstCycleInfo.ends()) {
                var allMatch = true;

                for (var i = 0; i < positions.size(); i++) {
                    var cycleInfo = cycleInfoList.get(i);
                    var value = (counter - cycleInfo.offSet() + end) % cycleInfo.cycleNumber();
                    if (!cycleInfo.ends().contains(value)) {
                        allMatch = false;
                        break;
                    }
                }

                if (allMatch) {
                    return counter + end;
                }
            }
            counter += firstCycleInfo.cycleNumber();
        }
    }

    private static CycleInfo getCycle(String startPosition, List<Integer> sequence, Map<String, Pair<String, String>> lookUp) {
        var position = startPosition;
        var seen = new HashMap<String, Long>();

        var ends = new ArrayList<Long>();
        var counter = 0L;
        while (true) {
            seen.put(position, counter);

            for (var instruction : sequence) {
                counter++;

                var value = lookUp.get(position);
                position = instruction == 'L' ? value.getFirst() : value.getSecond();
                if (position.charAt(2) == 'Z') {
                    ends.add(counter);
                }
            }

            if (seen.containsKey(position)) {
                var offset = seen.get(position);
                long cycleNumber = counter - offset;
                var cyclePositions = ends.stream().map(e -> e - offset).filter(e -> e >= 0 && e < cycleNumber).toList();
                return new CycleInfo(offset, cycleNumber, cyclePositions);
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

package day05;

import util.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Part2_improved {

    private record Translation(long start, long end, long shift) {
        private static Translation of(String line) {
            var parts = Arrays.stream(line.split(" ")).mapToLong(Long::valueOf).toArray();
            return new Translation(parts[1], parts[1] + parts[2] - 1, parts[0] - parts[1]);
        }
    }

    public record Interval(long start, long end) {
    }

    public static void main(String[] args) {
        var testInput = Utility.readLines("day05/test_2");
        Utility.check(46, (int) solve(testInput));

        var input = Utility.readLines("day05/input");
        System.out.println(solve(input));
    }

    private static long solve(List<String> input) {
        var seedInput = Arrays.stream(input.getFirst().split(": ")[1].split(" ")).map(Long::parseLong).toList();
        List<Interval> seedRanges = new ArrayList<>();

        for (int i = 0; i < seedInput.size(); i = i + 2) {
            seedRanges.add(new Interval(seedInput.get(i), seedInput.get(i) + seedInput.get(i + 1) - 1));
        }
        seedRanges = seedRanges.stream().sorted(Comparator.comparing(Interval::start)).toList();

        var sections = getSections(input);

        for (var section : sections) {
            seedRanges = getNextSeedRanges(seedRanges, section);
        }

        return seedRanges.stream().map(Interval::start).mapToLong(Long::longValue).min().orElse(0L);
    }

    private static List<List<Translation>> getSections(List<String> lines) {
        List<List<Translation>> sections = new ArrayList<>();
        List<Translation> section;

        var i = 2;
        while (i < lines.size()) {
            section = new ArrayList<>();
            while (i < lines.size() && !lines.get(i).isBlank()) {
                if (!Character.isAlphabetic(lines.get(i).charAt(0))) {
                    section.add(Translation.of(lines.get(i)));
                }
                i++;
            }
            section = section.stream().sorted(Comparator.comparing(Translation::start)).toList();
            sections.add(section);
            i++;
        }

        return sections;
    }

    private static List<Interval> getNextSeedRanges(List<Interval> seedRanges, List<Translation> section) {
        return seedRanges.stream()
                .map(r -> getNextSeedRange(r, section))
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Interval::start))
                .toList();
    }

    private static List<Interval> getNextSeedRange(Interval seedRange, List<Translation> section) {
        List<Interval> nextSeedRange = new ArrayList<>();
        var seedStart = seedRange.start();
        var seedEnd = seedRange.end();

        for (var translation : section) {
            var translationStart = translation.start();
            var translationEnd = translation.end();
            var shift = translation.shift();

            if (seedStart <= translationEnd && seedEnd >= translation.start()) {
                if (seedStart < translation.start()) {
                    nextSeedRange.add(new Interval(seedStart, translationStart - 1));
                }
                nextSeedRange.add(new Interval(Math.max(seedStart, translationStart) + shift, Math.min(seedEnd, translationEnd) + shift));

                seedStart = translationEnd + 1;
                if (seedStart > seedEnd) {
                    break;
                }
            }
        }

        if (seedStart <= seedEnd) {
            nextSeedRange.add(new Interval(seedStart, seedEnd));
        }
        return nextSeedRange;
    }
}

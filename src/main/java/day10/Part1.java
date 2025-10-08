package day10;

import util.Utility;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class Part1 {

    private enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT;

        Direction opposite() {
            return switch (this) {
                case UP -> DOWN;
                case RIGHT -> LEFT;
                case DOWN -> UP;
                case LEFT -> RIGHT;
            };
        }
    }

    private record Coordinate(int x, int y) {
        @Override
        public boolean equals(Object other) {
            if (other instanceof Coordinate(int otherX, int otherY)) {
                return otherX == this.x() && otherY == this.y();
            }
            return false;
        }

        public Coordinate next(Direction direction) {
            return switch (direction) {
                case UP -> new Coordinate(x - 1, y);
                case DOWN -> new Coordinate(x + 1, y);
                case LEFT -> new Coordinate(x, y - 1);
                case RIGHT -> new Coordinate(x, y + 1);
            };
        }

        public boolean isNextInBound(Direction direction, int maxX, int maxY) {
            return switch (direction) {
                case UP -> x > 0;
                case DOWN -> x < maxX - 1;
                case LEFT -> y > 0;
                case RIGHT -> y < maxY - 1;
            };
        }
    }

    public static void main(String[] args) {
        var testInput = Utility.readLines("day10/test_1");
        Utility.check(8, solve(testInput));

        var input = Utility.readLines("day10/input");
        System.out.println(solve(input));
    }

    private static int solve(List<String> input) {
        var start = getStart(input);

        return Stream.of(Direction.values())
                .map(d -> distanceToStart(input, d, start))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow();
    }

    private static Optional<Integer> distanceToStart(List<String> input, Direction direction, Coordinate start) {
        int maxY = input.getFirst().length();

        if (!start.isNextInBound(direction, input.size(), maxY)) {
            return Optional.empty();
        }

        var next = start.next(direction);
        if (getDirection(input, next, direction.opposite()).isEmpty()) {
            return Optional.empty();
        }

        return distanceToStart(input, next, direction.opposite(), start).map(d -> d / 2 + d % 2);
    }

    private static Optional<Integer> distanceToStart(List<String> input, Coordinate current, Direction from, Coordinate start) {
        var i = 0;
        while (!current.equals(start)) {
            var direction = getDirection(input, current, from);

            if (direction.isEmpty()) {
                return Optional.empty();
            }

            current = current.next(direction.get());
            from = direction.get().opposite();

            i++;
        }

        return Optional.of(i);
    }

    private static Optional<Direction> getDirection(List<String> input, Coordinate current, Direction from) {
        var tile = input.get(current.x()).charAt(current.y());

        Optional<Direction> nextDirection = switch (tile) {
            case '.' -> Optional.empty();
            case '-' -> getDirection(from, Direction.LEFT, Direction.RIGHT);
            case '|' -> getDirection(from, Direction.UP, Direction.DOWN);
            case 'F' -> getDirection(from, Direction.RIGHT, Direction.DOWN);
            case '7' -> getDirection(from, Direction.LEFT, Direction.DOWN);
            case 'L' -> getDirection(from, Direction.UP, Direction.RIGHT);
            case 'J' -> getDirection(from, Direction.UP, Direction.LEFT);
            default -> throw new IllegalStateException("Unexpected value: " + tile);
        };

        int maxY = input.getFirst().length();
        return nextDirection.filter(n -> current.isNextInBound(n, input.size(), maxY));
    }

    private static Optional<Direction> getDirection(Direction from, Direction one, Direction two) {
        if (Set.of(one, two).contains(from)) {
            return Optional.of(from == one ? two : one);
        } else {
            return Optional.empty();
        }
    }

    private static Coordinate getStart(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            var line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'S') {
                    return new Coordinate(i, j);
                }
            }
        }

        throw new IllegalStateException("No starting point found");
    }
}

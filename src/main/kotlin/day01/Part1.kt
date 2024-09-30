package day01

import println
import readInput

fun main() {
    fun solveLine(line: String): Int {
        val digits = line.toCharArray().filter { c -> Character.isDigit(c) }
        return digits.first().digitToInt() * 10 + digits.last().digitToInt()
    }

    fun solve(lines: List<String>): Int {
        return lines.sumOf { l -> solveLine(l) }
    }

    // test
    val testInput = readInput("day01/test_1")
    check(solve(testInput) == 142)

    val input = readInput("day01/input")
    solve(input).println()
}

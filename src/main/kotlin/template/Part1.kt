package template

import println
import readInput

fun main() {
    fun solveLine(line: String): Int {
        return 0
    }

    fun solve(lines: List<String>): Int {
        return lines.sumOf { l -> solveLine(l) }
    }

    // test
    val testInput = readInput("day0x/test_1")
    check(solve(testInput) == 0)

    val input = readInput("day0x/input")
    solve(input).println()
}

package day02

import println
import readInput

fun main() {

    fun isColorExceeded(amount: Int, color: String): Boolean {
        return when (color[0]) {
            'r' -> amount > 12
            'g' -> amount > 13
            'b' -> amount > 14

            else -> {
                throw IllegalStateException()
            }
        }
    }

    fun solveLine(line: String): Int {
        val parts = line.split(" ")
        val gameIndex = parts[1].substring(0, parts[1].length - 1).toInt()

        var exceeded = false;
        for (i in 2..<parts.size step 2) {
            exceeded = exceeded || isColorExceeded(parts[i].toInt(), parts[i + 1])
        }

        return if (exceeded) 0 else gameIndex;
    }

    fun solve(lines: List<String>): Int {
        return lines.sumOf { l -> solveLine(l) }
    }

    // test
    val testInput = readInput("day02/test_1")
    check(solve(testInput) == 8)

    val input = readInput("day02/input")
    solve(input).println()
}

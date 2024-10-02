package day02

import println
import readInput

fun main() {

    fun solveLine(line: String): Int {
        val parts = line.split(" ")

        var maxRed = 0
        var maxGreen = 0
        var maxBlue = 0

        for (i in 2..<parts.size step 2) {
            when (parts[i + 1][0]) {
                'r' -> maxRed = maxOf(maxRed, parts[i].toInt())
                'g' -> maxGreen = maxOf(maxGreen, parts[i].toInt())
                'b' -> maxBlue = maxOf(maxBlue, parts[i].toInt())
            }
        }

        return maxRed * maxGreen * maxBlue;
    }

    fun solve(lines: List<String>): Int {
        return lines.sumOf { l -> solveLine(l) }
    }

    // test
    val testInput = readInput("day02/test_2")
    check(solve(testInput) == 2286)

    val input = readInput("day02/input")
    solve(input).println()
}

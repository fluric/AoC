fun main() {
    fun solveLine(line: String): Int {
        val digits = line.toCharArray().filter { c -> Character.isDigit(c) }
        return digits.first().digitToInt() * 10 + digits.last().digitToInt()
    }

    fun solve(lines: List<String>): Int {
        return lines.sumOf { l -> solveLine(l) }
    }

    // test
    val testInput = readInput("Day01_1_test")
    check(solve(testInput) == 142)

    val input = readInput("Day01")
    solve(input).println()
}

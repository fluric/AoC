fun main() {
    val writtenDigits = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    fun modifyLine(line: String, index: Int): String {
        val foundDigit = writtenDigits.keys.firstOrNull { w -> line.startsWith(w, index) }

        return foundDigit
            ?.let { d -> line.replaceRange(index, index+1, writtenDigits[d].toString()) }
            ?: line
    }

    fun modifyLine(line: String): String {
        var index = 0
        var modifiedLine = line

        while (index < modifiedLine.length) {
            modifiedLine = modifyLine(modifiedLine, index)
            index++
        }

        return modifiedLine
    }

    fun solveLine(line: String): Int {
        val modifiedLine = modifyLine(line);

        val digits = modifiedLine.toCharArray().filter { c -> Character.isDigit(c) }
        return digits.first().digitToInt() * 10 + digits.last().digitToInt()
    }

    fun solve(lines: List<String>): Int {
        return lines.sumOf { l -> solveLine(l) }
    }

    // test
    val testInput = readInput("Day01_2_test")
    check(solve(testInput) == 281)

    val input = readInput("Day01")
    solve(input).println()
}

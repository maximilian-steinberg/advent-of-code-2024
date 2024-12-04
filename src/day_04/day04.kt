package day_04

import println
import readInput

fun main() {

    fun part1(input: List<String>): Int {
        val indexes = listOf(
            listOf(Pair(1, 0), Pair(2, 0), Pair(3, 0)),
            listOf(Pair(0, 1), Pair(0, 2), Pair(0, 3)),
            listOf(Pair(1, 1), Pair(2, 2), Pair(3, 3)),
            listOf(Pair(-1, 1), Pair(-2, 2), Pair(-3, 3)),
        )
        var wordCount = 0
        val regex = """XMAS|SAMX""".toRegex()

        input.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { charIndex, char ->
                indexes.forEach { pairList ->
                    val stringBuilder = StringBuilder().append(char)

                    pairList.forEach { pair ->
                        val newLineIndex = lineIndex + pair.first
                        val newCharIndex = charIndex + pair.second

                        if (newLineIndex in input.indices && newCharIndex in line.indices) {
                            stringBuilder.append(input[newLineIndex][newCharIndex])
                        }
                    }

                    if (regex.matches(stringBuilder.toString())) {
                        wordCount++
                    }
                }
            }
        }

        return wordCount
    }

    fun part2(input: List<String>): Int {
        val indexes = listOf(
            listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2)),
            listOf(Pair(0, 2), Pair(1, 1), Pair(2, 0)),
        )
        var wordCount = 0
        val regex = """MAS|SAM""".toRegex()

        input.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { charIndex, _ ->
                var pairWords = 0

                indexes.forEach { pairList ->
                    val stringBuilder = StringBuilder()

                    pairList.forEach {
                        val newLineIndex = lineIndex + it.first
                        val newCharIndex = charIndex + it.second

                        if (newLineIndex in input.indices && newCharIndex in line.indices) {
                            stringBuilder.append(input[newLineIndex][newCharIndex])
                        }
                    }

                    if (regex.matches(stringBuilder.toString())) {
                        pairWords++
                    }
                }

                if (pairWords == 2) {
                    wordCount++
                }
            }
        }

        return wordCount
    }

    val testInput = readInput("day_04/test")
    check(part1(testInput) == 18)

    // Read the input from the `src/input.txt` file.
    val input = readInput("day_04/input")
    part1(input).println()
    part2(input).println()
}

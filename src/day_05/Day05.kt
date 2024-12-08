package day_05

import println
import readInput

data class ParsedInput(
    val rules: List<Pair<Int, Int>>,
    val updates: List<List<Int>>
)

fun main() {

    fun parseInput(input: List<String>): ParsedInput {
        val rules = mutableListOf<Pair<Int, Int>>()
        val updates = mutableListOf<List<Int>>()

        input.forEach { line ->
            if (line.isNotEmpty()) {
                if ("|" in line) {
                    val rule = line.split("|")
                    rules.add(Pair(rule[0].toInt(), rule[1].toInt()))
                } else if (',' in line) {
                    updates.add(line.split(",").map { it.toInt() })
                }
            }
        }

        return ParsedInput(rules, updates)
    }


    fun part1(input: List<String>): Int {
        val (rules, updates) = parseInput(input)

        return updates.filter { update ->
            update.indices.all { index ->
                val page = update[index]
                rules.none { it.first == page && it.second in update && update.indexOf(it.second) < index }
            }
        }.sumOf { pages -> pages[(pages.size - 1) / 2] }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("day_05/test")
    check(part1(testInput) == 143)

// Read the input from the `src/input.txt` file.
    val input = readInput("day_05/input")
    part1(input).println()
    part2(input).println()
}

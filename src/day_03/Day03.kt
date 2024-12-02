package day_02

import println
import readInput

fun main() {


    fun part1(input: List<String>): Int {
        var countSafeInputs = 0

        input.map {
            val levels = it.split(" ")
        }

        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("day_03/test")
    check(part1(testInput) == 2)

    // Read the input from the `src/input.txt` file.
    val input = readInput("day_03/input")
    part1(input).println()
    part2(input).println()
}

package day_02

import println
import readInput

fun main() {


    fun part1(input: List<String>): Int {
        var countSafeLevels = 0

        input.map {
            if (Day02_Utils.isLevelSafe(it.split(" "))) {
                countSafeLevels += 1
            }
        }

        return countSafeLevels
    }

    fun part2(input: List<String>): Int {
        var countSafeLevels = 0

        input.map {
            if (Day02_Utils.isLevelSafeWithTolerance(it.split(" "))) {
                countSafeLevels += 1
            }
        }

        return countSafeLevels
    }

    val testInput = readInput("day_02/test")
    check(part1(testInput) == 2)

    // Read the input from the `src/input.txt` file.
    val input = readInput("day_02/input")
    part1(input).println()
    part2(input).println()
}

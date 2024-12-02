package day_01

import println
import readInput

fun main() {


    fun part1(input: List<String>): Int {
        val (leftSide, rightSide) = Day01_Utils.separateInput(input)

        leftSide.sort()
        rightSide.sort()

        var sum = 0

        for ((index, value) in leftSide.withIndex()) {
            val rightSideValue = rightSide[index]
            sum += if (rightSideValue < value) {
                value - rightSideValue
            } else {
                rightSideValue - value
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        val (leftSide, rightSide) = Day01_Utils.separateInput(input)
        var similarityScore = 0

        for (i in leftSide) {
            val findings = rightSide.count {it == i}
            similarityScore += findings * i
        }

        return similarityScore
    }

    // Read the input from the `src/input.txt` file.
    val input = readInput("day_01/input")
    part1(input).println()
    part2(input).println()
}

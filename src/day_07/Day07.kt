package day_07

import println
import readInput

fun main() {

    fun validateEquationPart1(acc: Long, testValue: Long, numbers: List<Long>): Boolean {
        return if (acc == testValue && numbers.isEmpty()) {
            true
        } else if (numbers.isEmpty() || acc > testValue) {
            false
        } else {
            validateEquationPart1(
                acc + numbers.first(),
                testValue,
                numbers.drop(1)
            ) || validateEquationPart1(acc * numbers.first(), testValue, numbers.drop(1))
        }
    }

    fun validateEquationPart2(acc: Long, testValue: Long, numbers: List<Long>): Boolean {
        return if (acc == testValue && numbers.isEmpty()) {
            true
        } else if (numbers.isEmpty() || acc > testValue) {
            false
        } else {
            validateEquationPart2(
                acc + numbers.first(),
                testValue,
                numbers.drop(1)
            ) || validateEquationPart2(
                acc * numbers.first(),
                testValue,
                numbers.drop(1)
            ) || validateEquationPart2(
                (acc.toString() + numbers.first().toString()).toLong(),
                testValue,
                numbers.drop(1)
            )
        }
    }

    fun validateEquations(input: List<String>, validate: (Long, Long, List<Long>) -> Boolean): Long {
        return input.map { line ->
            val (testValue, numberString) = line.split(": ")
            val numbers = numberString.split(" ").map { it.toLong() }
            testValue.toLong() to numbers
        }.filter { (testValue, numbers) -> validate(numbers.first(), testValue, numbers.drop(1)) }
            .sumOf { (testValue, _) -> testValue }
    }

    fun part1(input: List<String>): Long {
        return validateEquations(input, ::validateEquationPart1)
    }

    fun part2(input: List<String>): Long {
        return validateEquations(input, ::validateEquationPart2)
    }

    val testInput = readInput("day_07/test")
    check(part1(testInput) == 3749.toLong())

    // Read the input from the `src/input.txt` file.
    val input = readInput("day_07/input")
    part1(input).println()
    part2(input).println()
}

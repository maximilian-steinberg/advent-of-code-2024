package day_02

import println
import readInput

fun main() {


    fun part1(input: List<String>): Int {
        val regex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
        var sum = 0

        input.forEach { s ->
            regex.findAll(s).forEach { r ->
                sum += r.groupValues[1].toInt() * r.groupValues[2].toInt()
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        val regex = """mul\((\d{1,3}),(\d{1,3})\)|do\(\)|don't\(\)""".toRegex()
        var sum = 0
        var isEnabled = true

        input.forEach { s ->
            regex.findAll(s).forEach { r ->
                if(r.value == "do()") {
                    isEnabled = true
                } else if (r.value == "don't()") {
                    isEnabled = false
                } else {
                    if (isEnabled) {
                        sum += r.groupValues[1].toInt() * r.groupValues[2].toInt()
                    }
                }
            }
        }

        return sum
    }

    val testInput = readInput("day_03/test")
    //check(part1(testInput) == 2)

    // Read the input from the `src/input.txt` file.
    val input = readInput("day_03/input")
    part1(input).println()
    part2(input).println()
}

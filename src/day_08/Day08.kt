package day_08

import println
import readInput
import java.awt.geom.PathIterator
import kotlin.math.absoluteValue

fun main() {

    fun Pair<Int, Int>.isValid(input: List<String>): Boolean {
        return this.first >= 0 && this.first < input[0].length && this.second >= 0 && this.second < input.size
    }

    fun addAntiNodes(
        antiNodes: MutableSet<Pair<Int, Int>>,
        antenna: Pair<Int, Int>,
        rowDistance: Int,
        columnDistance: Int,
        input: List<String>,
        isForward: Boolean
    ) {
        var counter = 1
        while (true) {
            val antiNode = if (isForward) {
                Pair(
                    antenna.first + (counter * rowDistance) + rowDistance,
                    antenna.second + (counter * columnDistance) + columnDistance
                )
            } else {
                Pair(
                    antenna.first - (counter * rowDistance),
                    antenna.second - (counter * columnDistance)
                )
            }
            if (antiNode.isValid(input)) {
                antiNodes.add(antiNode)
                counter++
            } else {
                break
            }
        }
    }

    fun parseInput(input: List<String>): Map<Char, MutableList<Pair<Int, Int>>> {
        val antennas = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()

        input.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { charIndex, char ->
                if (char != '.') {
                    antennas.computeIfAbsent(char) { mutableListOf() }.add(Pair(charIndex, lineIndex))
                }
            }
        }

        return antennas
    }

    fun part1(input: List<String>): Int {
        val antennasMap = parseInput(input)
        val antiNodes = mutableListOf<Pair<Int, Int>>()

        antennasMap.forEach { (_, antennas) ->
            antennas.forEachIndexed { antennaIndex, antenna ->
                for (nextIndex in antennaIndex + 1 until antennas.size) {
                    val rowDistance = (antennas[nextIndex].first - antenna.first)
                    val columnDistance = (antennas[nextIndex].second - antenna.second)

                    val potentialNodes = listOf(
                        Pair(antenna.first - rowDistance, antenna.second - columnDistance),
                        Pair(antenna.first + 2 * rowDistance, antenna.second + 2 * columnDistance)
                    )

                    potentialNodes.forEach { node ->
                        if (node.isValid(input)) {
                            antiNodes.add(node)
                        }
                    }
                }
            }
        }

        return antiNodes.distinct().size
    }

    fun part2(input: List<String>): Int {
        val antennasMap = parseInput(input)
        val antiNodes = mutableSetOf<Pair<Int, Int>>()

        antennasMap.forEach { (_, antennas) ->
            antennas.forEachIndexed { antennaIndex, antenna ->
                if (antennas.size >= 2) {
                    antiNodes.add(antenna.first to antenna.second)
                }

                for (nextIndex in antennaIndex + 1 until antennas.size) {
                    val rowDistance = (antennas[nextIndex].first - antenna.first)
                    val columnDistance = (antennas[nextIndex].second - antenna.second)

                    addAntiNodes(antiNodes, antenna, rowDistance, columnDistance, input, false)
                    addAntiNodes(antiNodes, antenna, rowDistance, columnDistance, input, true)
                }
            }
        }

        return antiNodes.distinct().size
    }

    val testInput = readInput("day_08/test")
    println("Test Part1: ${part1(testInput)}")
    check(part1(testInput) == 14)
    println("Test Part2: ${part2(testInput)}")
    check(part2(testInput) == 34)

// Read the input from the `src/input.txt` file.
    val input = readInput("day_08/input")
    part1(input).println()
    part2(input).println()

}

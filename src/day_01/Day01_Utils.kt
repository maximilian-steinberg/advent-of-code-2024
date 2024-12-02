package day_01

class Day01_Utils {
    companion object {
        fun separateInput(input: List<String>): Pair<MutableList<Int>, MutableList<Int>> {
            val leftSide = mutableListOf<Int>()
            val rightSide = mutableListOf<Int>()

            input.map {
                val line =  it.split("   ")
                leftSide.add(line[0].toInt())
                rightSide.add(line[1].toInt())
            }

            leftSide.sort()
            rightSide.sort()

            return  Pair(leftSide, rightSide)
        }
    }

}
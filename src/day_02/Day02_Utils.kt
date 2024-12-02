package day_02

class Day02_Utils {
    companion object {
        private fun isAscending(level: List<String>): Boolean {
            level.zipWithNext().map {
                if (it.second.toInt() - it.first.toInt() !in 1..3) {
                    return false
                }
            }

            return true
        }

        private fun isDescending(level: List<String>): Boolean {
            level.zipWithNext().map {
                if (it.second.toInt() - it.first.toInt() !in -1 downTo -3) {
                    return false
                }
            }

            return true
        }

        fun isLevelSafe(level: List<String>): Boolean {
            return isDescending(level) || isAscending(level)
        }

        fun isLevelSafeWithTolerance(level: List<String>): Boolean {
            for (index in level.indices) {
                val droppedIndex = level.filterIndexed { i, _ -> i != index }
                if (isDescending(droppedIndex) || isAscending(droppedIndex)) {
                    return true
                }
            }
            return false
        }
    }
}
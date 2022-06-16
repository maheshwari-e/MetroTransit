package transformer

import model.Direction

object DirectionMapTransformer {

    operator fun invoke(from: List<Direction>?): Map<String, Int> {
        return from.orEmpty().map { direction ->
            val directionName = direction.directionName?.takeIf { it.isNotEmpty() } ?: return@map null
            directionName to direction.directionId
        }.filterNotNull().toMap()
    }
}


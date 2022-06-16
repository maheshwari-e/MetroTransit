package transformer

import model.Stop

object StopMapTransformer {

    operator fun invoke(from: List<Stop>?): Map<String, String> {
        return from.orEmpty().map { stop ->
            val placeCode = stop.placeCode?.takeIf { it.isNotEmpty() } ?: return@map null
            val description = stop.description?.takeIf { it.isNotEmpty() } ?: return@map null
            description to placeCode
        }.filterNotNull().toMap()
    }
}

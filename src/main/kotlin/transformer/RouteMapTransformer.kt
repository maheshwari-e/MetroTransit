package transformer

import model.Route

object RouteMapTransformer {

    operator fun invoke(from: List<Route>?): Map<String, String> {
        val result = from.orEmpty().map {  route ->
            val routeLabel = route.routeLabel?.takeIf { it.isNotEmpty() } ?: return@map null
            val routeId = route.routeId?.takeIf { it.isNotEmpty() } ?: return@map null
            routeLabel to routeId
        }.filterNotNull().toMap()

        return result
    }
}

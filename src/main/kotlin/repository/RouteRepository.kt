package repository

import api.MetroTransitApiService
import model.Route
import transformer.RouteMapTransformer

/**
 * returns map of all the [Route] associated by [Route.routeLabel] to [Route.routeId]
 */

interface RouteRepository {
    fun getAllRoute(): Map<String, String>
}

class RouteRepositoryImpl(
    private val service: MetroTransitApiService,
    private val transformer: (List<Route>?) -> Map<String, String> = RouteMapTransformer::invoke
) : RouteRepository {
    private val routeMap = mutableMapOf<String, String>()

    override fun getAllRoute(): Map<String, String> {
       return routeMap.takeIf { it.isNotEmpty() } ?: fetch().also { routeMap.putAll(it) }
    }

    private fun fetch():Map<String, String>{
        val routes = service.getRoutes().execute().body()
        return transformer(routes)
    }
}

fun getRoute(
    name: String?,
    repository: RouteRepository,
    readLine: () -> String? = ::readLine
): String{
    val routes = repository.getAllRoute()
    var id = routes[name]

    while(id == null) {
        println("\n\n----- Enter route from the options below -----\n\n")
        println("      ${routes.keys.joinToString(", ")}\n\n")
        val route = readLine()?.trim()
        id = routes[route]

        if(id  == null) {
            println("\n----- Wrong route try again -----\n")
        }
    }

    return id
}


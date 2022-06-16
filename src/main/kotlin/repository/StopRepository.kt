package repository

import api.MetroTransitApiService
import model.Stop
import transformer.StopMapTransformer

/**
 * returns map of all the [Stop] associated by [Stop.description] to [Stop.placeCode]
 */

interface StopRepository {
    fun getAllStop(routeId:String, directionId:String):Map<String, String>
}

class StopRepositoryImpl(
    private val service: MetroTransitApiService,
    private val transformer:(List<Stop>?) -> Map<String, String> = StopMapTransformer::invoke
) : StopRepository {

    override fun getAllStop(routeId: String, directionId: String): Map<String, String> {
        val stops =  service.getStops(
            routeId = routeId,
            directionId = directionId
        ).execute().body()
        return transformer(stops)
    }
}

fun getStop(
    routeId: String,
    directionId: String,
    name:String?,
    repository: StopRepository,
    readLine: () -> String? = ::readLine
): String {
    val stops = repository.getAllStop(routeId, directionId)
    var id = stops[name]

    while(id  == null){
        println("\n\n----- Enter stop from the options below: -----\n\n")
        println("      ${stops.keys.joinToString(", ")}\n")
        val stop = readLine()?.trim()
        id = stops[stop]

        if(id == null){
            println("\n----- Wrong stop try again -----\n")
        }
    }

    return id
}

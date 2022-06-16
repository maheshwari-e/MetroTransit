package repository

import api.MetroTransitApiService
import model.Direction
import transformer.DirectionMapTransformer

/**
 * returns map of all the [Direction] associated by [Direction.directionName] to [Direction.directionId]
 */

interface DirectionRepository {
    fun getAllDirection(routeId: String): Map<String, Int>
}

class DirectionRepositoryImpl(
    private val service: MetroTransitApiService,
    private val transformer: (List<Direction>?) -> Map<String, Int> = DirectionMapTransformer::invoke
) : DirectionRepository{

    override fun getAllDirection(routeId:String): Map<String, Int> {
        val directions = service.getDirections(routeId).execute().body()
        return transformer(directions)
    }
}

fun getDirection(
    routeId: String,
    name:String?,
    repository: DirectionRepository,
    readLine: () -> String? = ::readLine
): Int {
    val directions = repository.getAllDirection(routeId)
    var id = directions[name]

    while(id == null) {
        println("\n\n----- Enter direction from the options below -----\n\n")
        println("      ${directions.keys.joinToString(", ")}\n\n")
        val direction = readLine()?.trim()
        id = directions[direction]

        if(id == null) {
            println("\n----- Wrong Direction try again -----\n")
        }
    }

    return id
}

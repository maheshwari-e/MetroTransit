import api.MetroTransitApiService
import repository.*

fun main(args: Array<String>) {

   val service = MetroTransitApiService.create()

   val (ROUTE, STOP, DIRECTION) = args
      .map { it.trim() }
      .takeIf { it.isNotEmpty() } ?: listOf("", "", "")

   val routeId = getRoute(
      name = ROUTE,
      repository = RouteRepositoryImpl(service)
   )

   val directionId = getDirection(
      routeId = routeId,
      name = DIRECTION,
      repository = DirectionRepositoryImpl(service),
   )

   val stopId = getStop(
      routeId = routeId,
      directionId = directionId.toString(),
      name = STOP,
      repository = StopRepositoryImpl(service)
   )

   val nextDeparture = getWaitingTime(
      routeId = routeId,
      directionId = directionId.toString(),
      stopId = stopId,
      repository = DepartureRepositoryImpl(service)
   )

   println("\n\n      $nextDeparture")

   println("\n\n----- Press enter to exit -----\n\n")

   readLine()
}


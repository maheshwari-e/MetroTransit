package repository

import api.MetroTransitApiService
import model.NextTrips
import transformer.DepartureMapTransformer
import java.util.*

/**
 * returns map of all the [NextTrips] as Long
 */

interface DepartureRepository {
    fun getAllTime(routeId: String, directionId: String, stopId: String): List<Long>
}

class DepartureRepositoryImpl(
    private val service: MetroTransitApiService,
    private val transformer:(NextTrips?) -> List<Long> = DepartureMapTransformer::invoke
) : DepartureRepository{

    override fun getAllTime(routeId: String, directionId: String, stopId: String): List<Long> {
        val nextTrips =  service.getDepartureTimes(
            routeId = routeId,
            directionId = directionId,
            stopId = stopId
        ).execute().body()
        return transformer(nextTrips)
    }
}

fun getWaitingTime(
    routeId: String,
    directionId: String,
    stopId: String,
    repository: DepartureRepository,
    currentTime: Long = System.currentTimeMillis()
): String {
    val times = repository.getAllTime(
        routeId = routeId,
        directionId = directionId,
        stopId = stopId
    )

    return if(times.isEmpty()) {
        "The Last bus for the day already left or No More bus available"
    } else {
        calculateDepartureTime(
            departureTime = times.first(),
            currentTime = currentTime
        )
    }
}

fun calculateDepartureTime(departureTime: Long, currentTime: Long): String {
    val timeInMinutes = (convertToLocalTimeZone(departureTime) - currentTime) / (1000 * 60)

    return when{
        timeInMinutes <= 0 -> "Arriving soon!"
        timeInMinutes == 1L -> "Due in a minute"
        else -> "$timeInMinutes Minutes"
    }
}

fun convertToLocalTimeZone(time: Long): Long {
    return Date(time.times(1000)).time
}

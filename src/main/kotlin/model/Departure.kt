package model

import com.squareup.moshi.Json

data class Departure(
    @Json(name = "departure_time")
    val departureTime: Long
)

data class NextTrips(
    @Json(name = "departures")
    val departures: List<Departure>?
)

package model

import com.squareup.moshi.Json

data class Route (
    @Json(name = "route_id") val routeId: String?,
    @Json(name = "route_label") val routeLabel: String?
)

package model

import com.squareup.moshi.Json

data class Direction(
    @Json(name = "direction_id")
    val directionId: Int,

    @Json(name = "direction_name")
    val directionName: String?
)


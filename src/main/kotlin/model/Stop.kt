package model

import com.squareup.moshi.Json

data class Stop(
    @Json(name = "place_code")
    val placeCode: String?,

    @Json(name = "description")
    val description: String?
)

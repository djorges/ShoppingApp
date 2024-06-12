package data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingDto (
    @SerialName("rate")
    val rate: Double,
    @SerialName("count")
    val count:Int
)

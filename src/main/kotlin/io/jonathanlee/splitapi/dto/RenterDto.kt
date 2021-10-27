package io.jonathanlee.splitapi.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.jonathanlee.splitapi.model.Renter
import java.util.*

/**
 * Data transfer object used for transporting Renter model data.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
data class RenterDto(
    @JsonProperty("renter_id") val renterId: String,
    @JsonProperty("created_at") val createdAt: Date,
    val name: String,
    @JsonProperty("user_id") val userId: String
) {
    constructor(renter: Renter) : this(
        renter.renterId,
        renter.createdAt,
        renter.name,
        renter.username
    )
}

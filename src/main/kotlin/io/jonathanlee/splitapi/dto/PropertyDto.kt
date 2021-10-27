package io.jonathanlee.splitapi.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.jonathanlee.splitapi.model.Property
import java.util.*

/**
 * Data transfer object used for transporting Property model data.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
data class PropertyDto(
    @JsonProperty("property_id") val propertyId: String,
    @JsonProperty("created_at") val createdAt: Date,
    val name: String,
    val address: String,
    @JsonProperty("user_id") val userId: String
) {
    constructor(property: Property) : this(
        property.propertyId,
        property.createdAt,
        property.name,
        property.address,
        property.username
    )
}

package io.jonathanlee.splitapi.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

/**
 * Entity used to represent a property.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@Entity
data class Property(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    @JsonProperty("property_id") @Column(name = "property_id", unique = true) val propertyId: String,
    @JsonProperty("created_at") @Column(name = "created_at") val createdAt: Date,
    var name: String,
    var address: String,
    val username: String
)

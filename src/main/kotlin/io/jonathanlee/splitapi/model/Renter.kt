package io.jonathanlee.splitapi.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

/**
 * Entity used to represent a renter.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@Entity
data class Renter(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    @JsonProperty("renter_id") @Column(name = "renter_id", unique = true) val renterId: String,
    @JsonProperty("created_at") @Column(name = "created_at") val createdAt: Date,
    var name: String,
    val username: String
)

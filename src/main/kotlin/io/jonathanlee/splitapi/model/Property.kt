package io.jonathanlee.splitapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.jonathanlee.splitapi.model.auth.User
import java.util.*
import javax.persistence.*

@Entity
data class Property(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    @JsonProperty("property_id") @Column(name = "property_id", unique = true) val propertyId: String,
    @Column(name = "created_at") val createdAt: Date,
    var name: String,
    var address: String,
    @JsonIgnore @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL],
        optional = false,
        targetEntity = User::class
    ) val user: User
)

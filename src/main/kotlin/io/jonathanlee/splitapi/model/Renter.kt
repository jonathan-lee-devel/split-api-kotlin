package io.jonathanlee.splitapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.jonathanlee.splitapi.model.auth.User
import java.util.*
import javax.persistence.*

@Entity
data class Renter(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    @JsonProperty("renter_id") @Column(name = "renter_id", unique = true) val renterId: String,
    @JsonProperty("created_at") @Column(name = "created_at") val createdAt: Date,
    var name: String,
    @JsonIgnore @OneToOne(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL],
        optional = false,
        targetEntity = User::class
    ) val user: User
)

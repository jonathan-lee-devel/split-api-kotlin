package io.jonathanlee.splitapi.model.auth

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

/**
 * Entity used to represent a user.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    @JsonProperty("user_id") @Column(name = "user_id", unique = true) val userId: String,
    @Column(unique = true) val email: String,
    var password: String,
    var enabled: Boolean,
    @Column(name = "created_at") val createdAt: Date,
    @JsonIgnore
    @OneToOne(
        fetch = FetchType.EAGER,
        mappedBy = "user",
        cascade = [CascadeType.ALL]
    ) var verificationToken: VerificationToken?,
    @JsonIgnore
    @OneToOne(
        fetch = FetchType.EAGER,
        mappedBy = "user",
        cascade = [CascadeType.ALL]
    ) val passwordResetToken: PasswordResetToken?
)

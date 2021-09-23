package io.jonathanlee.splitapi.model.auth

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
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

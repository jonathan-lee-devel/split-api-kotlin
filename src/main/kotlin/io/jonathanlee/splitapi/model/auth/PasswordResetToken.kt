package io.jonathanlee.splitapi.model.auth

import java.util.*
import javax.persistence.*

@Entity
data class PasswordResetToken(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    val token: String,
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id") val user: User,
    val expiryDate: Date
) {
    constructor(token: String, user: User) : this(0L, token, user, Date())
}

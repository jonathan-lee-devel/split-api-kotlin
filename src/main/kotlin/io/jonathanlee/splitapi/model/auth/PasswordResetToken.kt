package io.jonathanlee.splitapi.model.auth

import java.util.*
import javax.persistence.*

/**
 * Entity representing a password reset token used in cases where user wishes to reset password.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@Entity
data class PasswordResetToken(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    val token: String,
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id") val user: User,
    var expiryDate: Date
) {
    constructor(token: String, user: User) : this(0L, token, user, Date())
}

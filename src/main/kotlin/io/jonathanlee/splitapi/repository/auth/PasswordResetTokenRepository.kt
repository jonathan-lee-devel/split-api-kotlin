package io.jonathanlee.splitapi.repository.auth

import io.jonathanlee.splitapi.model.auth.PasswordResetToken
import org.springframework.data.jpa.repository.JpaRepository

interface PasswordResetTokenRepository : JpaRepository<PasswordResetToken, Long> {

    fun findByToken(token: String): PasswordResetToken

    fun findByUserId(userId: Long): PasswordResetToken

}

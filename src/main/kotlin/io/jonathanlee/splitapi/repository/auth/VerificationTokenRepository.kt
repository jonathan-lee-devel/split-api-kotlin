package io.jonathanlee.splitapi.repository.auth

import io.jonathanlee.splitapi.model.auth.VerificationToken
import org.springframework.data.jpa.repository.JpaRepository

interface VerificationTokenRepository : JpaRepository<VerificationToken, Long> {

    fun findByToken(token: String): VerificationToken

    fun findByUserId(userId: Long): VerificationToken

}

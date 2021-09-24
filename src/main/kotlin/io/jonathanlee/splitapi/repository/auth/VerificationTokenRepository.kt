package io.jonathanlee.splitapi.repository.auth

import io.jonathanlee.splitapi.model.auth.VerificationToken
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository interface used to interact with verification token entities.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface VerificationTokenRepository : JpaRepository<VerificationToken, Long> {

    /**
     * Used to find verification token entity by token contents.
     *
     * @param token contents of verification token to be obtained.
     * @return verification token obtained by token contents.
     */
    fun findByToken(token: String): VerificationToken

    /**
     * Used to find verification token entity by associated user ID.
     *
     * @param userId ID of associated user for verification token entity.
     * @return verification token obtained by associated user ID.
     */
    fun findByUserId(userId: Long): VerificationToken

}

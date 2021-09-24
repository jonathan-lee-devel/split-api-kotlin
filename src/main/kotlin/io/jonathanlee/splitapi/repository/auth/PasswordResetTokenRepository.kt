package io.jonathanlee.splitapi.repository.auth

import io.jonathanlee.splitapi.model.auth.PasswordResetToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.lang.Nullable

/**
 * Repository interface used to interact with password reset token entities.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface PasswordResetTokenRepository : JpaRepository<PasswordResetToken, Long> {

    /**
     * Used to find password reset token by token.
     *
     * @param token used to find password reset token
     * @return password reset token obtained according to provided token
     */
    fun findByToken(token: String): PasswordResetToken

    /**
     * Used to find password reset token by user.
     *
     * @param userId ID of user entity to which the password reset token entity is attached.
     * @return password reset token entity attached to user entity.
     */
    @Nullable
    fun findByUserId(userId: Long): PasswordResetToken

}

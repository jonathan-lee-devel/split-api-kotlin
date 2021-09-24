package io.jonathanlee.splitapi.repository.auth

import io.jonathanlee.splitapi.model.auth.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.lang.Nullable

/**
 * Repository interface used to interact with user entities.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface UserRepository : JpaRepository<User, Long> {

    /**
     * Used to find user entity by user email.
     *
     * @param email email of user entity to be returned.
     * @return user entity obtained by email.
     */
    @Nullable
    fun findByEmail(email: String): User

    /**
     * Used to find user entity by user ID.
     *
     * @param userId ID of user entity to be returned.
     * @return user entity obtained by user ID.
     */
    fun findByUserId(userId: String): User

}

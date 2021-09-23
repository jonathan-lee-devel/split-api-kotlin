package io.jonathanlee.splitapi.repository.auth

import io.jonathanlee.splitapi.model.auth.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.lang.Nullable

interface UserRepository : JpaRepository<User, Long> {

    @Nullable
    fun findByEmail(email: String): User

}

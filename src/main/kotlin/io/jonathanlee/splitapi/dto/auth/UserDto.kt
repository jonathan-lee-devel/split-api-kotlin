package io.jonathanlee.splitapi.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty
import io.jonathanlee.splitapi.model.auth.User
import java.util.*

/**
 * Data transfer object used for transporting User model data.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
data class UserDto(
    @JsonProperty("user_id") val userId: String,
    val email: String,
    @JsonProperty("created_at") val createdAt: Date
) {
    constructor(user: User) : this(
        user.userId,
        user.email,
        user.createdAt
    )
}
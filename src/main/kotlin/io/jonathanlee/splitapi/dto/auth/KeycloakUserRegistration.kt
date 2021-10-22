package io.jonathanlee.splitapi.dto.auth

import javax.validation.constraints.Email

/**
 * Form filled in with user-entered data representing a user to be registered.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
class KeycloakUserRegistration(
    val firstName: String,
    val lastName: String,
    @field:Email val email: String,
    var enabled: Boolean,
    var emailVerified: Boolean,
    val username: String,
    val credentials: List<KeycloakUserRegistrationCredentials>
)

data class KeycloakUserRegistrationCredentials(
    var type: String,
    val value: String,
    var temporary: Boolean
)

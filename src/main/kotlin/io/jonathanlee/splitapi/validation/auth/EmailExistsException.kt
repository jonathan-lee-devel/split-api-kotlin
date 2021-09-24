package io.jonathanlee.splitapi.validation.auth

/**
 * Exception representing the case where an email already exists in the system.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
class EmailExistsException(
    message: String
) : Throwable(message)

package io.jonathanlee.splitapi.service.auth

import io.jonathanlee.splitapi.dto.auth.UserDto
import io.jonathanlee.splitapi.form.auth.UserRegistrationForm
import java.util.*

/**
 * Registration service used to manage registration of users.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface RegistrationService {

    /**
     * Used to register new users according to information provided in user registration form.
     *
     * @param userRegistrationForm information provided in user registration form.
     * @return user obtained according to information provided in user registration form.
     */
    fun registerNewUser(userRegistrationForm: UserRegistrationForm): Optional<UserDto>

    /**
     * Used to confirm new users according to verification token contents.
     *
     * @param token contents of verification token.
     * @return user confirmed according to contents of verification token.
     */
    fun confirmNewUser(token: String): Optional<UserDto>

}

package io.jonathanlee.splitapi.service.auth

import io.jonathanlee.splitapi.dto.auth.UserDto
import io.jonathanlee.splitapi.form.auth.PasswordResetForm
import java.util.*

/**
 * Service used to manage user data.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
interface UserService {

    /**
     * Method used to obtain available users.
     */
    fun getUsers(): Collection<UserDto>

    /**
     * Method used to obtain a specific user by email.
     *
     * @param email of specific user to be obtained.
     * @return user data contained in a UserDto.
     */
    fun getUserByEmail(email: String): Optional<UserDto>

    /**
     * Method used to obtain a specific user by user ID.
     *
     * @param userId ID of user to be obtained.
     * @return user data contained in a UserDto.
     */
    fun getUserByUserId(userId: String): Optional<UserDto>

    /**
     * Method used to reset password for user by email.
     *
     * @param email email of user for which password is to be reset.
     * @return user data contained in a UserDto.
     */
    fun resetPasswordByEmail(email: String): Optional<UserDto>

    /**
     * Method used to confirm password reset for user.
     *
     * @param tokenContents contents of password reset token.
     * @param passwordResetForm password reset form data used to reset user password.
     * @return user data contained in a UserDto.
     */
    fun confirmPasswordReset(tokenContents: String, passwordResetForm: PasswordResetForm): Optional<UserDto>

}

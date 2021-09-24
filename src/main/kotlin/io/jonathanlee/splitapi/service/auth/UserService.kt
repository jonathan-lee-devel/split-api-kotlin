package io.jonathanlee.splitapi.service.auth

import io.jonathanlee.splitapi.dto.auth.UserDto

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
    fun getUserByEmail(email: String): UserDto

    /**
     * Method used to obtain a specific user by user ID.
     *
     * @param userId ID of user to be obtained.
     * @return user data contained in a UserDto.
     */
    fun getUserByUserId(userId: String): UserDto

}

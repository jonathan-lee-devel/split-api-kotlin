package io.jonathanlee.splitapi.service.auth.impl

import io.jonathanlee.splitapi.dto.auth.UserDto
import io.jonathanlee.splitapi.repository.auth.UserRepository
import io.jonathanlee.splitapi.service.auth.UserService
import org.springframework.stereotype.Service

/**
 * Implementation of service used to manage user data.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    /**
     * Implementation of method used to obtain available users.
     *
     * @return collection of available users.
     */
    override fun getUsers(): Collection<UserDto> {
        return this.userRepository.findAll().map { UserDto(it) }
    }

    /**
     * Implementation of method used obtain a specific user by email.
     *
     * @param email of user specified.
     * @return specific user data obtained according to provided email.
     */
    override fun getUserByEmail(email: String): UserDto {
        return UserDto(this.userRepository.findByEmail(email))
    }

    /**
     * Implementation of method used to obtain a specific user by user ID.
     *
     * @param userId ID of user specified.
     * @return specific user data obtained according to provided user ID.
     */
    override fun getUserByUserId(userId: String): UserDto {
        return UserDto(this.userRepository.findByUserId(userId))
    }

}

package io.jonathanlee.splitapi.service.auth.impl

import io.jonathanlee.splitapi.dto.auth.UserDto
import io.jonathanlee.splitapi.repository.auth.UserRepository
import io.jonathanlee.splitapi.service.auth.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun getUsers(): Collection<UserDto> {
        return this.userRepository.findAll().map { UserDto(it) }
    }

    override fun getUserByEmail(email: String): UserDto {
        return UserDto(this.userRepository.findByEmail(email))
    }

    override fun getUserByUserId(userId: String): UserDto {
        return UserDto(this.userRepository.findByUserId(userId))
    }

}

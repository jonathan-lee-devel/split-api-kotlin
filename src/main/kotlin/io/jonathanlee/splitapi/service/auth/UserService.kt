package io.jonathanlee.splitapi.service.auth

import io.jonathanlee.splitapi.dto.auth.UserDto

interface UserService {

    fun getUsers(): Collection<UserDto>

    fun getUserByEmail(email: String): UserDto

    fun getUserByUserId(userId: String): UserDto

}

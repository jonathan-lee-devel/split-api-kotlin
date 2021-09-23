package io.jonathanlee.splitapi.service.auth.impl

import io.jonathanlee.splitapi.repository.auth.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class UserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = username?.let { this.userRepository.findByEmail(it) }
            ?: throw UsernameNotFoundException("No user found with username: $username")

        return org.springframework.security.core.userdetails.User(
            user.email,
            user.password,
            user.enabled,
            true,
            true,
            true,
            Collections.emptyList()
        )
    }

}

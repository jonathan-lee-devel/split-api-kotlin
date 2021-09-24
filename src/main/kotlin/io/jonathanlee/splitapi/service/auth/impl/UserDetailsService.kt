package io.jonathanlee.splitapi.service.auth.impl

import io.jonathanlee.splitapi.repository.auth.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

/**
 * Custom user details service used by Spring Security to manage user log-ins.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@Service
@Transactional
class UserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    /**
     * Implementation of method used to load user details object according to provided username.
     *
     * @param username of user to be loaded.
     * @return user details object loaded according to provided username.
     */
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

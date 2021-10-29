package io.jonathanlee.splitapi.service.auth

import io.jonathanlee.splitapi.dto.auth.RegisterDto
import org.springframework.http.HttpStatus

interface RegistrationService {

    fun register(registrationData: RegisterDto): HttpStatus

}

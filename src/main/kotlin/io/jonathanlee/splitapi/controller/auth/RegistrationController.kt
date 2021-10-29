package io.jonathanlee.splitapi.controller.auth

import io.jonathanlee.splitapi.dto.auth.RegisterDto
import io.jonathanlee.splitapi.service.auth.RegistrationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/register")
class RegistrationController(private val registrationService: RegistrationService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody registerData: RegisterDto): ResponseEntity<Void> {
        return ResponseEntity.status(this.registrationService.register(registerData)).build()
    }

}

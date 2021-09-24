package io.jonathanlee.splitapi.controller.auth

import io.jonathanlee.splitapi.dto.auth.UserDto
import io.jonathanlee.splitapi.form.auth.UserRegistrationForm
import io.jonathanlee.splitapi.service.auth.RegistrationService
import io.jonathanlee.splitapi.service.auth.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/register")
class RegistrationController(
    private val registrationService: RegistrationService,
    private val userService: UserService
) {

    @PostMapping
    fun register(@Valid @RequestBody userRegistrationForm: UserRegistrationForm): ResponseEntity<UserDto> {
        return ResponseEntity.ok(
            this.userService.getUserByUserId(
                this.registrationService.registerNewUser(
                    userRegistrationForm
                ).userId
            )
        )
    }

    @GetMapping("/confirm")
    fun confirm(@RequestParam token: String): ResponseEntity<UserDto> {
        return ResponseEntity.ok(this.userService.getUserByUserId(this.registrationService.confirmNewUser(token).userId))
    }

}

package io.jonathanlee.splitapi.controller.auth

import io.jonathanlee.splitapi.dto.auth.UserDto
import io.jonathanlee.splitapi.form.auth.UserRegistrationForm
import io.jonathanlee.splitapi.service.auth.RegistrationService
import io.jonathanlee.splitapi.service.auth.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * REST controller used for user registration.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@RestController
@RequestMapping("/register")
class RegistrationController(
    private val registrationService: RegistrationService,
    private val userService: UserService
) {

    /**
     * POST request used to register a new user based on the information passed to the controller via the
     * user registration form request body.
     *
     * @param userRegistrationForm user registration form used to obtain information on newly registered user.
     * @return UserDto contained in a ResponseEntity with newly registered user's information.
     */
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

    /**
     * GET request used to confirm the registration of newly registered user via the token request parameter.
     *
     * @param token user registration verification token used to confirm registration of new user
     * @return UserDto contained in a ResponseEntity with newly confirmed registered user's information
     */
    @GetMapping("/confirm")
    fun confirm(@RequestParam token: String): ResponseEntity<UserDto> {
        return ResponseEntity.ok(this.userService.getUserByUserId(this.registrationService.confirmNewUser(token).userId))
    }

}

package io.jonathanlee.splitapi.controller.auth

import io.jonathanlee.splitapi.dto.auth.KeycloakUserRegistration
import io.jonathanlee.splitapi.dto.auth.KeycloakUserRegistrationCredentials
import io.jonathanlee.splitapi.form.auth.UserRegistrationForm
import io.jonathanlee.splitapi.service.auth.KeycloakAdminService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono
import javax.validation.Valid

/**
 * REST controller used for user registration.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@RestController
@RequestMapping("/register")
class RegistrationController(
    private val keycloakAdminService: KeycloakAdminService,
    private val webClient: WebClient
) {

    /**
     * POST request used to register a new user based on the information passed to the controller via the
     * user registration form request body.
     *
     * @param userRegistration user registration form used to obtain information on newly registered user.
     * @return UserDto contained in a ResponseEntity with newly registered user's information.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@Valid @RequestBody userRegistration: UserRegistrationForm): ResponseEntity<Void> {

        if (!userRegistration.validate()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }

        val keycloakUserRegistration = KeycloakUserRegistration(
            userRegistration.firstName,
            userRegistration.lastName,
            userRegistration.email,
            enabled = true,
            emailVerified = false,
            userRegistration.username,
            listOf(KeycloakUserRegistrationCredentials("password", userRegistration.password, false))
        )

        val response: Mono<ResponseEntity<Void>> = this.webClient.post()
            .uri("/auth/admin/realms/split/users")
            .header(
                HttpHeaders.AUTHORIZATION,
                "Bearer ${this.keycloakAdminService.obtainAccessToken()}"
            )
            .body(Mono.just(keycloakUserRegistration), KeycloakUserRegistration::class.java)
            .retrieve()
            .toBodilessEntity()
        try {
            response.block()
        } catch (ex: WebClientResponseException) {
            println("Exception: $ex")
            return when (ex.statusCode) {
                HttpStatus.UNAUTHORIZED -> {
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                }
                HttpStatus.CONFLICT -> {
                    ResponseEntity.status(HttpStatus.CONFLICT).build()
                }
                else -> {
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                }
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

}

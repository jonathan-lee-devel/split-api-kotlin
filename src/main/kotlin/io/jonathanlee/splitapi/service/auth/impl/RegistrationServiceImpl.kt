package io.jonathanlee.splitapi.service.auth.impl

import io.jonathanlee.splitapi.dto.auth.KeycloakUserRegistration
import io.jonathanlee.splitapi.dto.auth.KeycloakUserRegistrationCredentials
import io.jonathanlee.splitapi.dto.auth.RegisterDto
import io.jonathanlee.splitapi.service.auth.KeycloakAdminService
import io.jonathanlee.splitapi.service.auth.RegistrationService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono

@Service
class RegistrationServiceImpl(
    private val keycloakAdminService: KeycloakAdminService,
    private val webClient: WebClient
) : RegistrationService {

    override fun register(registrationData: RegisterDto): HttpStatus {
        if (!registrationData.validate()) return HttpStatus.BAD_REQUEST

        val keycloakUserRegistration = KeycloakUserRegistration(
            registrationData.firstName,
            registrationData.lastName,
            registrationData.email,
            enabled = true,
            emailVerified = false,
            registrationData.email,
            listOf(KeycloakUserRegistrationCredentials("password", registrationData.password, false))
        )

        try {
            val response: Mono<ResponseEntity<Void>> = this.webClient.post()
                .uri("$AUTH_ADMIN_API_URL/users")
                .header(HttpHeaders.AUTHORIZATION, "Bearer ${this.keycloakAdminService.obtainAccessToken()}")
                .body(Mono.just(keycloakUserRegistration), KeycloakUserRegistration::class.java)
                .retrieve()
                .toBodilessEntity()
            response.block()
        } catch (ex: WebClientResponseException) {
            return when (ex.statusCode) {
                HttpStatus.CREATED -> ex.statusCode
                HttpStatus.CONFLICT -> ex.statusCode
                else -> HttpStatus.INTERNAL_SERVER_ERROR
            }
        }

        return HttpStatus.CREATED
    }

    companion object {
        const val AUTH_ADMIN_API_URL = "/auth/admin/realms/split"
    }

}

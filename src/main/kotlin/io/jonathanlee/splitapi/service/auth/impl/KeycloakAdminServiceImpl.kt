package io.jonathanlee.splitapi.service.auth.impl

import com.fasterxml.jackson.annotation.JsonProperty
import io.jonathanlee.splitapi.service.auth.KeycloakAdminService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Service
class KeycloakAdminServiceImpl(private val webClient: WebClient) : KeycloakAdminService {

    @Value("\${auth.keycloak.admin.clientSecret}")
    private lateinit var clientSecret: String

    override fun obtainAccessToken(): String? {

        return webClient
            .post()
            .uri("/auth/realms/split/protocol/openid-connect/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(
                BodyInserters
                    .fromFormData("grant_type", GRANT_TYPE)
                    .with("client_id", CLIENT_ID)
                    .with("client_secret", clientSecret)
            )
            .retrieve()
            .bodyToMono(KeycloakAdminTokenRequest::class.java)
            .block()?.accessToken
    }

    companion object {
        private const val GRANT_TYPE = "client_credentials"
        private const val CLIENT_ID = "admin-cli"
    }

}

data class KeycloakAdminTokenRequest(@JsonProperty("access_token") val accessToken: String)

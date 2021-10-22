package io.jonathanlee.splitapi.service.auth

interface KeycloakAdminService {

    fun obtainAccessToken(): String?

}

package io.jonathanlee.splitapi.interceptor.auth

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthorizationInterceptor(
    private val objectMapper: ObjectMapper
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val principal = SecurityContextHolder.getContext().authentication.principal
        if (principal != null && principal !is String) {
            val jwt = principal as Jwt

            var map = this.objectMapper.readValue(jwt.claims["resource_access"].toString(), Map::class.java)
            map = map["split-api"] as Map<*, *>?
            val roles = map["roles"] as List<*>

            request.setAttribute(rolesAttribute, roles)
        }

        return true
    }

    companion object {
        const val rolesAttribute = "roles"
    }

}
